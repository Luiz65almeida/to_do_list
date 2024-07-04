package com.almeida.to_do_list.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.almeida.to_do_list.common.enuns.RoleEnum;
import com.almeida.to_do_list.common.exeption.ResourceNotFoundException;
import com.almeida.to_do_list.dto.UserDto;
import com.almeida.to_do_list.model.Role;
import com.almeida.to_do_list.model.Users;
import com.almeida.to_do_list.payload.response.MessageResponse;
import com.almeida.to_do_list.repository.RoleRepository;
import com.almeida.to_do_list.repository.UserRepository;
import com.almeida.to_do_list.security.jwt.JwtUtils;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  public List<UserDto> findAll() {
    List<Users> users = userRepository.findAll();
    return users.stream()
        .map(user -> modelMapper.map(user, UserDto.class))
        .collect(Collectors.toList());
  }

  public UserDto findById(Long id) {
    Optional<Users> optUser = userRepository.findById(id);

    if (optUser.isEmpty()) {
      throw new ResourceNotFoundException("Não foi possível encontrar o usuário com o id: " + id);
    }
    return modelMapper.map(optUser.get(), UserDto.class);
  }

  public UserDto findByEmail(String email) {
    Optional<Users> optUser = userRepository.findByEmail(email);

    if (optUser.isEmpty()) {
      throw new ResourceNotFoundException("Não foi possível encontrar o usuário com o email: " + email);
    }
    return modelMapper.map(optUser.get(), UserDto.class);
  }

  public List<UserDto> findByName(String name) {
    Optional<Users> users = userRepository.findByUsername(name);

    if (users.isEmpty()) {
      throw new ResourceNotFoundException("Não foi possível encontrar usuários com o nome: " + name);
    }
    return users.stream()
        .map(user -> modelMapper.map(user, UserDto.class))
        .collect(Collectors.toList());
  }

  public MessageResponse registerUser(UserDto userDto) {
    if (userRepository.existsByUsername(userDto.getUsername())) {
      return new MessageResponse("Error: Username is already taken!");
    }

    if (userRepository.existsByEmail(userDto.getEmail())) {
      return new MessageResponse("Error: Email is already in use!");
    }

    Users user = new Users(userDto.getUsername(), userDto.getEmail(),
        encoder.encode(userDto.getPassword()));

    Set<String> strRoles = userDto.getRoles();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);
            break;
          case "mod":
            Role modRole = roleRepository.findByName(RoleEnum.ROLE_MODERATOR)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(modRole);
            break;
          default:
            Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    user.setDateRegister(LocalDateTime.now());
    userRepository.save(user);

    return new MessageResponse("User registered successfully!");
  }

  public UserDto update(Long id, UserDto userDto) {
    Optional<Users> optionalUser = userRepository.findById(id);
    if (optionalUser.isEmpty()) {
      throw new ResourceNotFoundException("Não foi possível encontrar o usuário com o id: " + id);
    }

    Users entity = userRepository.getReferenceById(id);
    updateUserDetails(entity, userDto);
    Users updatedEntity = userRepository.save(entity);
    return modelMapper.map(updatedEntity, UserDto.class);
  }

  public ResponseEntity<String> delete(Long id) {
    Optional<Users> user = userRepository.findById(id);

    if (id == null || user.isEmpty()) {
      throw new ResourceNotFoundException("Não foi possível encontrar o usuário com o id: " + id);
    }

    userRepository.deleteById(id);
    return new ResponseEntity<>("Usuário deletado com sucesso", HttpStatus.OK);
  }

  private void updateUserDetails(Users entity, UserDto userDto) {
    entity.setUsername(userDto.getUsername());
    entity.setPassword(userDto.getPassword());
  }
}
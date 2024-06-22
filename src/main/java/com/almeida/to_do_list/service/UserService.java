package com.almeida.to_do_list.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.almeida.to_do_list.common.exeption.ResourceBadRequestException;
import com.almeida.to_do_list.common.exeption.ResourceNotFoundException;
import com.almeida.to_do_list.dto.UserDto;
import com.almeida.to_do_list.model.Users;
import com.almeida.to_do_list.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;

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
    List<Users> users = userRepository.findByName(name);

    if (users.isEmpty()) {
      throw new ResourceNotFoundException("Não foi possível encontrar usuários com o nome: " + name);
    }
    return users.stream()
        .map(user -> modelMapper.map(user, UserDto.class))
        .collect(Collectors.toList());
  }

  public UserDto insert(UserDto userDto) {
    validateName(userDto);

    Optional<Users> optUser = userRepository.findByEmail(userDto.getEmail());

    if (optUser.isPresent()) {
      throw new ResourceBadRequestException("Já existe um usuário cadastrado com esse email: " + userDto.getEmail());
    }

    Users user = modelMapper.map(userDto, Users.class);

    user.setDateRegister(LocalDateTime.now());
    user = userRepository.save(user);
    return modelMapper.map(user, UserDto.class);
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
    entity.setName(userDto.getName());
    entity.setPassword(userDto.getPassword());
  }

  private void validateName(UserDto userDto) {
    if (userDto.getName() == null || userDto.getEmail() == null) {
      throw new RuntimeException("Name e email são obrigatórios");
    }
  }
}
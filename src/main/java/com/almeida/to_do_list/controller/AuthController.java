package com.almeida.to_do_list.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.almeida.to_do_list.dto.UserDto;
import com.almeida.to_do_list.payload.response.JwtResponse;
import com.almeida.to_do_list.repository.RoleRepository;
import com.almeida.to_do_list.repository.UserRepository;
import com.almeida.to_do_list.security.jwt.JwtUtils;
import com.almeida.to_do_list.service.AuthService;
import com.almeida.to_do_list.service.UserService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  AuthService authService;

  @Autowired
  UserService userService;

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserDto userDto) {
    JwtResponse jwtResponse = authService.authenticateUser(userDto);
    return ResponseEntity.ok(jwtResponse);
  }
}
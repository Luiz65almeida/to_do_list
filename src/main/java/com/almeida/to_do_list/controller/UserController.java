package com.almeida.to_do_list.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.almeida.to_do_list.dto.UserDto;
import com.almeida.to_do_list.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<UserDto>> findAll() {
    List<UserDto> users = userService.findAll();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> findById(@PathVariable Long id) {
    UserDto user = userService.findById(id);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<UserDto> findByEmail(@PathVariable String email) {
    UserDto user = userService.findByEmail(email);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<List<UserDto>> findByName(@PathVariable String name) {
    List<UserDto> users = userService.findByName(name);
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<UserDto> insert(@RequestBody UserDto userDto) {
    UserDto createdUser = userService.insert(userDto);
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable Long id) {
    return userService.delete(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto userDto) {
    UserDto updatedUser = userService.update(id, userDto);
    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
  }

}

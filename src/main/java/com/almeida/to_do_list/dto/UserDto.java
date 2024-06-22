package com.almeida.to_do_list.dto;

import java.time.LocalDateTime;

public class UserDto {

  private Long id;

  private String name;

  private String email;

  private String password;

  private LocalDateTime dateRegister;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public LocalDateTime getDateRegister() {
    return dateRegister;
  }

  public void setDateRegister(LocalDateTime dateRegister) {
    this.dateRegister = dateRegister;
  }
}
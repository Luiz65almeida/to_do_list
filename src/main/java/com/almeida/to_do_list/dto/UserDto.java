package com.almeida.to_do_list.dto;

import java.util.Date;

public class UserDto {

  private Long id;

  private String name;

  private String email;

  private String password;

  private Date dateRegister;

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

  public Date getDateRegister() {
    return dateRegister;
  }

  public void setDateRegister(Date dateRegister) {
    this.dateRegister = dateRegister;
  }

}

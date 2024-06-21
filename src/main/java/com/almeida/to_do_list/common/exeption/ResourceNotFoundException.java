package com.almeida.to_do_list.common.exeption;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String mensagem) {
    super(mensagem);
  }
}

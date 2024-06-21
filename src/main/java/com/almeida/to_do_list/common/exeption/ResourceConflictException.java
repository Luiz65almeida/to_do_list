package com.almeida.to_do_list.common.exeption;

public class ResourceConflictException extends RuntimeException {

  public ResourceConflictException(String mensagem) {
    super(mensagem);
  }
}

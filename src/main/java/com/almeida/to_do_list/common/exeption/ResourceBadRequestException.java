package com.almeida.to_do_list.common.exeption;

public class ResourceBadRequestException extends RuntimeException {

  public ResourceBadRequestException(String mensagem) {
    super(mensagem);
  }
}

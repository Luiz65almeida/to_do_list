package com.almeida.to_do_list.common.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceBadRequestException.class)
  public ResponseEntity<?> handleResourceBadRequestException(ResourceBadRequestException ex, WebRequest request) {
    String errorMessage = ex.getMessage();
    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    String errorMessage = ex.getMessage();
    return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ResourceConflictException.class)
  public ResponseEntity<?> handleResourceConflictException(ResourceConflictException ex, WebRequest request) {
    String errorMessage = ex.getMessage();
    return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
    String errorMessage = ex.getMessage();
    return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

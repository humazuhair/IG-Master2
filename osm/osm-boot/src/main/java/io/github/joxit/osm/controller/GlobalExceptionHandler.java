package io.github.joxit.osm.controller;

import org.springframework.http.ResponseEntity;

/**
 * C'est ici que vous devez gérer les exceptions qui sont levés. Utilisation de ControllerAdvice et ExceptionHandler.
 */
public class GlobalExceptionHandler {
  public ResponseEntity<Object> illegalArgumentException(IllegalArgumentException ex) {
    return null;
  }
}

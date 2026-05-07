package com.lafachada.gateway.Exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

@RestControllerAdvice
public class GestorGlobalExcepciones {

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<Object> manejarAccesoRecurso(ResourceAccessException ex) {
        Map<String, Object> cuerpo = new LinkedHashMap<>();
        cuerpo.put("fecha", LocalDateTime.now());
        cuerpo.put("estado", HttpStatus.SERVICE_UNAVAILABLE);
        cuerpo.put("error", ex.getLocalizedMessage());

        return new ResponseEntity<>(cuerpo, HttpStatus.SERVICE_UNAVAILABLE);
    }
}

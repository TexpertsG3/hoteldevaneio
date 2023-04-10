package br.com.tex.hoteldevaneio.controller.advice;

import br.com.tex.hoteldevaneio.model.dto.erro.ErroOutputDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity erroNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity erroNotFoundCadastra() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity erroBadRequest(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(ex.getFieldErrors().stream().map(e -> new ErroOutputDTO(e.getField(), e.getDefaultMessage())).toList());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity erroConstraintViolation(SQLIntegrityConstraintViolationException ex) {
        String message = ex.getMessage();
        String columnName = message.substring(message.lastIndexOf("for column '") + 48, message.lastIndexOf("_"));
        return ResponseEntity.badRequest().body(new ErroOutputDTO(columnName, "Já existe um cpf com este valor."));
    }
}

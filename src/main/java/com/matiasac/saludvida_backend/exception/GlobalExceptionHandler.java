package com.matiasac.saludvida_backend.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntidadNotFound(
        NotFoundException exception,
        HttpServletRequest request
    ) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidacionErrorResponse> handleValidacionError(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        Map<String, List<String>> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
                ));

        ValidacionErrorResponse response = new ValidacionErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Error de validación de los campos",
                errors,
                request.getRequestURI()
        );
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException exception,
            HttpServletRequest request
    ) {
        String mensaje = exception.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                mensaje,
                request.getRequestURI()
        );
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(RecursoDuplicadoException.class)
    public ResponseEntity<ValidacionErrorResponse> handleRecursoDuplicado(
            RecursoDuplicadoException exception,
            HttpServletRequest request
    ) {
        ValidacionErrorResponse response = new ValidacionErrorResponse(
                HttpStatus.CONFLICT.value(),
                exception.getMessage(),
                Map.of("error", List.of("Conflicto de integridad con datos existentes")),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}

package com.devsuperior.demo.controllers.handlers;

import com.devsuperior.demo.dto.EntityFieldErrorDTO;
import com.devsuperior.demo.dto.StandardErrorDTO;
import com.devsuperior.demo.dto.ValidationErrorDTO;
import com.devsuperior.demo.services.exceptions.DatabaseException;
import com.devsuperior.demo.services.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Clock;
import java.time.Instant;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardErrorDTO> notFoundException(NotFoundException exception,
                                                              HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardErrorDTO response = buildExceptionResponse(
                status,
                request,
                exception.getMessage()
        );

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardErrorDTO> notFoundException(DatabaseException exception,
                                                              HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardErrorDTO response = buildExceptionResponse(
                status,
                request,
                exception.getMessage()
        );

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> notFoundException(MethodArgumentNotValidException exception,
                                                                HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationErrorDTO response = (ValidationErrorDTO) buildExceptionResponse(
                status,
                request,
                exception.getMessage()
        );
        exception.getBindingResult().getFieldErrors().forEach(e -> response.addErrors(
                new EntityFieldErrorDTO(e.getField(), e.getObjectName())
        ));

        return ResponseEntity.status(status).body(response);
    }

    private <T extends RuntimeException> StandardErrorDTO buildExceptionResponse(
            HttpStatus status,
            HttpServletRequest request,
            String exceptionMessage) {
        StandardErrorDTO response = new StandardErrorDTO();
        response.setCode(status.value());
        response.setError(status.name());
        response.setTimestamp(Instant.now(Clock.systemUTC()));
        response.setMessage(exceptionMessage);
        response.setPath(request.getContextPath());

        return response;
    }

}

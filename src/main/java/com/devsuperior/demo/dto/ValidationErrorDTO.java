package com.devsuperior.demo.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class ValidationErrorDTO extends StandardErrorDTO {

    private Set<EntityFieldErrorDTO> errors = new HashSet<>();

    public ValidationErrorDTO() {
    }

    public ValidationErrorDTO(Integer code,
                              String error,
                              Instant timestamp,
                              String message,
                              String path) {
        super(code, error, timestamp, message, path);
    }

    public Set<EntityFieldErrorDTO> getErrors() {
        return errors;
    }

    public void addErrors(EntityFieldErrorDTO error) {
        errors.add(error);
    }

}

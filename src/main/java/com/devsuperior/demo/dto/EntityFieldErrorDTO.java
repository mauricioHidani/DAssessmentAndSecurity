package com.devsuperior.demo.dto;

import java.util.Set;

public class EntityFieldErrorDTO {

    private String fieldName;
    private String message;

    public EntityFieldErrorDTO() {
    }

    public EntityFieldErrorDTO(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

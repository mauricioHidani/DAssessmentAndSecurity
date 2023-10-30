package com.devsuperior.demo.dto;

import java.time.Instant;

public class StandardErrorDTO {

    private Integer code;
    private String error;
    private Instant timestamp;
    private String message;
    private String path;

    public StandardErrorDTO() {
    }

    public StandardErrorDTO(Integer code, String error, Instant timestamp, String message, String path) {
        this.code = code;
        this.error = error;
        this.timestamp = timestamp;
        this.message = message;
        this.path = path;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}

package com.greta.eshop_api.exposition.dtos;

import java.time.LocalDateTime;

public class ApiResponse {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String path;
    private Object data;

    public ApiResponse() {}

    public ApiResponse(int status, String message, String path, Object data) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.path = path;
        this.data = data;
    }

    // GETTERS & SETTERS

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }

    public Object getData() { return data; }
    public void setData(Object data) { this.data = data; }
}

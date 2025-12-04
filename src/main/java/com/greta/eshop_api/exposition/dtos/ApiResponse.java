package com.greta.eshop_api.exposition.dtos;

import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;

public class ApiResponse<T> {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String path;
    private T data;

    public ApiResponse() {}

    public ApiResponse(int status, String message, String path, T data) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.path = path;
        this.data = data;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }

    public static <T> ResponseEntity<ApiResponse<T>> ok(T data) {
        ApiResponse<T> response = new ApiResponse<>(200, "OK", null, data);
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> ok(String message, String path, T data) {
        ApiResponse<T> response = new ApiResponse<>(200, message, path, data);
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(T data) {
        ApiResponse<T> response = new ApiResponse<>(201, "CREATED", null, data);
        return ResponseEntity.status(201).body(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> error(int status, String message, String path) {
        ApiResponse<T> response = new ApiResponse<>(status, message, path, null);
        return ResponseEntity.status(status).body(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(String message, String path, T data) {
        ApiResponse<T> response = new ApiResponse<>(201, message, path, data);
        return ResponseEntity.status(201).body(response);
    }

}


package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    private int code = 200;
    private String status = "success";
    private String message = "ok";
    private T data;

    public static <T> Response<T> success(int code, String status, String message, T data) {
        return new Response<>(code, status, message, data);
    }

    public static <T> Response<T> success(String message, T data) {
        return success(200, "success", message, data);
    }

    public static <T> Response<T> success(T data) {
        return success(200, "success", "OK", data);
    }

    public static <T> Response<T> success(String message) {
        return success(message, null);
    }

    public static <T> Response<T> success() {
        return success(null);
    }

    public static <T> Response<T> error(int code, String status, String message) {
        return new Response<>(code, status, message, null);
    }

    public static <T> Response<T> error(int code, String message) {
        return new Response<>(code, "error", message, null);
    }

    public static <T> Response<T> error() {
        return error(400, "Something went wrong");
    }
}

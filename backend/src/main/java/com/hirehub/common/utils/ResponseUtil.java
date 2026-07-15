package com.hirehub.common.utils;

import com.hirehub.common.response.ApiResponse;

public class ResponseUtil {

    public static <T> ApiResponse<T> success(T data, String message) {

        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();

    }

    public static <T> ApiResponse<T> error(String message) {

        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .build();

    }

}
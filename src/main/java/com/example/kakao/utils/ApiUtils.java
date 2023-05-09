package com.example.kakao.utils;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;

public class ApiUtils {

    public static <T> ApiResult<T> success(T response) {
        return new ApiResult<>(true, response, null);
    }

    public static ApiResult<?> error(String message, HttpStatus status) {
        return new ApiResult<>(false, null, new ApiError(message, status));
    }

    /**
     * Result 반환
     * @param <T>
     */
    public static class ApiResult<T> {
        private final boolean success;
        private final T response;
        private final ApiError error;

        // 생성자
        public ApiResult(boolean success, T response, ApiError error) {
            this.success = success;
            this.response = response;
            this.error = error;
        }

        // Getter
        public boolean isSuccess() {
            return success;
        }

        public T getResponse() {
            return response;
        }

        public ApiError getError() {
            return error;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                    .append("success", success)
                    .append("response", response)
                    .append("error", error)
                    .toString();
        }
    }

    /**
     * Error message, status 반환
     */
    public static class ApiError {
        private final String message;
        private final int status;

        // 생성자
        public ApiError(String message, HttpStatus status) {
            this.message = message;
            this.status = status.value();
        }

        // Getter
        public String getMessage() {
            return message;
        }

        public int getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                    .append("message", message)
                    .append("status", status)
                    .toString();
        }
    }
}

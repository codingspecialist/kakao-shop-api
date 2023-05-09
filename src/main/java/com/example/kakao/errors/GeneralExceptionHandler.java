package com.example.kakao.errors;

import com.example.kakao.utils.ApiUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.example.kakao.utils.ApiUtils.error;

@ControllerAdvice
public class GeneralExceptionHandler {

    private ResponseEntity<ApiUtils.ApiResult<?>> newResponse(Throwable throwable, HttpStatus status) {
        return newResponse(throwable.getMessage(), status);
    }

    private ResponseEntity<ApiUtils.ApiResult<?>> newResponse(String message, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(error(message, status), headers, status);
    }

    /**
     * for Product, Order
     * @param e
     * @return
     */
    @ExceptionHandler({
            NotFoundException.class
    })
    public ResponseEntity<?> handleNotFoundException(Exception e) {
        return newResponse(e, HttpStatus.NOT_FOUND);
    }
}

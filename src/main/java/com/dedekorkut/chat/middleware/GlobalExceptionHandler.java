package com.dedekorkut.chat.middleware;

import com.dedekorkut.chat.common.ErrorResponse;
import com.dedekorkut.chat.common.WillfulException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({WillfulException.class})
    public ResponseEntity<ErrorResponse> handleWillfulException(WillfulException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

}

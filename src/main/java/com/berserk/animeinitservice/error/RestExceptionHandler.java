package com.berserk.animeinitservice.error;

import java.io.IOException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { IOException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError yearConstraintViolationExceptio(IOException ex) {
		return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
    }
}

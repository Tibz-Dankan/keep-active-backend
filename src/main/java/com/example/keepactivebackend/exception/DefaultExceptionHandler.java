package com.example.keepactivebackend.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(OpenApiResourceNotFoundException.class)
    public ResponseEntity<AppError> handleException(
            OpenApiResourceNotFoundException e, HttpServletRequest request
    ) {
        AppError appError = new AppError(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now());

        return new ResponseEntity<>(appError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<AppError> handleException(
            BadCredentialsException e, HttpServletRequest request
    ) {
        AppError appError = new AppError(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.UNAUTHORIZED.value(),
                LocalDateTime.now());

        return new ResponseEntity<>(appError, HttpStatus.UNAUTHORIZED);
    }



    @ExceptionHandler(InternalServerErrorException.class)
//    @ExceptionHandler(OpenApiC)
    public ResponseEntity<AppError> handleInternalServerErrorException(
            InternalServerErrorException e, HttpServletRequest request
    ) {
        AppError appError = new AppError(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now());

        return new ResponseEntity<>(appError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<AppError> handleNotAuthorizedException(
            NotAuthorizedException e, HttpServletRequest request
    ) {
        AppError appError = new AppError(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.FORBIDDEN.value(),
                LocalDateTime.now());

        return new ResponseEntity<>(appError, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<AppError> handleBadRequestException(
            BadRequestException e, HttpServletRequest request
    ) {
        AppError appError = new AppError(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now());

        return new ResponseEntity<>(appError, HttpStatus.BAD_REQUEST);
    }
}

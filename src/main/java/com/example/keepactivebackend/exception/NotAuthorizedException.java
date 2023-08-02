package com.example.keepactivebackend.exception;

public class NotAuthorizedException extends RuntimeException  {
    public NotAuthorizedException(String message) {
        super(message);
    }
}

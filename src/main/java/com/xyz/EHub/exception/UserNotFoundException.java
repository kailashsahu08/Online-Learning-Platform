package com.xyz.EHub.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String userNotFound) {
        super(userNotFound);
    }
}

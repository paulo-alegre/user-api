package com.cognizant.sg.userapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User not found exception for user end point
 *
 * @author Adrian Alegre
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(final String errorMessage) {
        super(errorMessage);
    }
}

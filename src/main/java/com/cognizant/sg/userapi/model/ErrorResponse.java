package com.cognizant.sg.userapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Error Response Model
 *
 * @author Adrian Alegre
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private String statusCode;
    private String message;
}

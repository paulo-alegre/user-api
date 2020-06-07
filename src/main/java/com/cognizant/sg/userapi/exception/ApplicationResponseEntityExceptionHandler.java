package com.cognizant.sg.userapi.exception;

import com.cognizant.sg.userapi.enums.ErrorMessages;
import com.cognizant.sg.userapi.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Global Application Exception Handler
 *
 * @author Adrian Alegre
 */
@RestControllerAdvice
public class ApplicationResponseEntityExceptionHandler {

    /**
     * User not found exception for User end point
     *
     * @param ex
     * @return ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(final UserNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorMessages.NOT_FOUND.getStatusCode(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Priority Level Exception Handler
     *
     * @param ex
     * @param request
     * @return ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(final Exception ex, final WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorMessages.INTERNAL_SERVER_ERROR.getStatusCode(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Run Time Exception Handler
     *
     * @param e
     * @return ResponseEntity<String>
     */
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRunTimeException(final RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

}

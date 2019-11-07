package com.byinal.rockpaperscissors.infrastructure.interceptor;

import com.byinal.rockpaperscissors.application.model.response.Response;
import com.byinal.rockpaperscissors.application.model.response.ResponseStatus;
import com.byinal.rockpaperscissors.domain.exception.GameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestControllerExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response> handleException(IllegalArgumentException ex) {
        String message = ex.getMessage();
        logger.warn("IllegalArgumentException occured: {}", message);
        Response response = createErrorResponse(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response> handleException(HttpMessageNotReadableException ex) {
        String message = ex.getMessage();
        logger.warn("HttpMessageNotReadableException occured: {}", message, ex);
        Response response = createErrorResponse(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(GameException.class)
    public ResponseEntity<Response> handleException(GameException ex) {
        String message = ex.getMessage();
        logger.warn("GameException occured: {}", message);
        Response response = createErrorResponse(message);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception ex) {
        String message = "General error occured";
        logger.warn("Exception occured: {}", message, ex);
        Response response = createErrorResponse(message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    private Response createErrorResponse(String message) {
        Response response = new Response();
        response.setStatus(ResponseStatus.FAILURE.name());
        response.setErrorMessage(message);
        return response;
    }
}

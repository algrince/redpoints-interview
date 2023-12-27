package com.redpoints.interview.advice;

import com.redpoints.interview.exceptions.InvalidIdException;
import com.redpoints.interview.exceptions.MovieNotFoundException;
import com.redpoints.interview.exceptions.UpdateWithWrongMovieException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleMovieNotFoundException(
            MovieNotFoundException e) {
        logger.warn("Failed to access an entity by request: {}", e.getLocalizedMessage());
    }

    @ExceptionHandler(InvalidIdException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleInvalidIdException(
            InvalidIdException e) {
        logger.warn(e.getLocalizedMessage());
    }

    @ExceptionHandler(UpdateWithWrongMovieException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleUpdateWithWrongMovieException(
            UpdateWithWrongMovieException e) {
        logger.warn(e.getLocalizedMessage());
    }
}

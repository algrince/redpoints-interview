package com.redpoints.interview.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
//    The use of logger from third parties (for example, lombok) could be
//    a better solution for delivering error messages to console

    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleMovieNotFoundException(
            MovieNotFoundException e) {
        System.out.println(e.getLocalizedMessage());
    }

    @ExceptionHandler(InvalidIdException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleInvalidIdException(
            InvalidIdException e) {
        System.out.println(e.getLocalizedMessage());
    }

    @ExceptionHandler(UpdateWithWrongMovieException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleUpdateWithWrongMovieException(
            UpdateWithWrongMovieException e) {
        System.out.println(e.getLocalizedMessage());
    }
}

package com.redpoints.interview.validators;

import com.redpoints.interview.exceptions.UpdateWithWrongMovieException;
import org.springframework.stereotype.Component;


@Component
public class MovieValidator {

    public void validateMovieForUpdate(Long movieEntityToUpdateId, Long pathVariableId) {
        if (!movieEntityToUpdateId.equals(pathVariableId)) {
            throw new UpdateWithWrongMovieException(String.format(
                    "There is an attempt to update a movie (with id: %d) with incorrect id: %d",
                    movieEntityToUpdateId, pathVariableId));
        }
    }

}

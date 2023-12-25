package com.redpoints.interview.validators;

import com.redpoints.interview.exceptions.InvalidIdException;
import com.redpoints.interview.exceptions.UpdateWithWrongMovieException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MovieValidator {

    public void validateMovieForUpdate(Long movieEntityToUpdateId, Long pathVariableId) {
        if (!movieEntityToUpdateId.equals(pathVariableId)) {
            throw new UpdateWithWrongMovieException(String.format(
                    "There is an attempt to update a movie (with id: %d) with incorrect id: %d",
                    movieEntityToUpdateId, pathVariableId));
        }

    }

    public void validateIdNotNull(Long id) {
        System.out.println(id);
        if (Objects.isNull(id)) {
            throw new InvalidIdException("Movie id cannot be null");
        }
    }
}

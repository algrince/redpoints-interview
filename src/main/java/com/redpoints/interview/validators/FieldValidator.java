package com.redpoints.interview.validators;

import com.redpoints.interview.exceptions.InvalidIdException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class FieldValidator {

    // This validator is used for checking for not null id to ensure
    // the application is functioning correctly. According to MovieEntity
    // other fields can be null. However, if necessary, this class can be completed
    // with other field validations.

    public void validateIdNotNull(Long id) {
        if (Objects.isNull(id)) {
            throw new InvalidIdException("Movie id cannot be null");
        }
    }

}

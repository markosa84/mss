package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

public interface Validator<T> {

    void validate(T t) throws IncorrectEnteredDataException;

}

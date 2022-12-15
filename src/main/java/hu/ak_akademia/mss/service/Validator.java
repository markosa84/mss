package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

public interface Validator<T> {

    void validate(T t) throws IncorrectEnteredDataException;
}

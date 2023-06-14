package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

public interface ValidatorSample<T> {

    void validate(Object t) throws IncorrectEnteredDataException;

}

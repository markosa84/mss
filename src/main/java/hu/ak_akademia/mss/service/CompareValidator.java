package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

public interface CompareValidator<T> {
    void validate(T t1, T t2) throws IncorrectEnteredDataException;
}

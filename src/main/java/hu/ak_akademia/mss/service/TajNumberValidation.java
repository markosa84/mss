package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

public class TajNumberValidation {
    public void validateTajNumber(String tajNumber) throws IncorrectEnteredDataException {
        if(tajNumber == null || !tajNumber.matches("^\\d{3}-\\d{3}-\\d{3}$")) { // valid TAJ e.g.: 123-456-789
            throw new IncorrectEnteredDataException("A megadott TAJ nem felel meg a formai követelményeknek!");
        }
    }
}

package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

public class PlaceOfBirthValidation {
    public void validatePlaceOfBirth(String placeOfBirth) throws IncorrectEnteredDataException {
        if(placeOfBirth == null || !placeOfBirth.matches("^(\\p{L}" + //must start with a letter
                "[\\p{L}\\p{Mn}\\s'-.]{0,98}"+
                "[\\p{L}.])$")) { //must end with a letter or a dot(for cities like Washington D.C.)
            throw new IncorrectEnteredDataException("A megadott születési hely érvénytelen!");
        }
    }
}

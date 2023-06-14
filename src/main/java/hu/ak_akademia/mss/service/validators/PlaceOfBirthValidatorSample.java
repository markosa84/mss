package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.service.ValidatorSample;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

class PlaceOfBirthValidatorSample implements ValidatorSample<Object> {
    @Override
    public void validate(Object placeOfBirth) throws IncorrectEnteredDataException {
        var birth = (String) placeOfBirth;
        if (birth == null || !birth.matches("^(\\p{L}" + //must start with a letter
                "[\\p{L}\\p{Mn}\\s'-.]{0,98}" +
                "[\\p{L}.])$")) { //must end with a letter or a dot(for cities like Washington D.C.)
            throw new IncorrectEnteredDataException("birthplaceError", "Invalid! Correct form is e.g.: Budapest");
        }
    }

}

package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.user.Client;
import hu.ak_akademia.mss.service.Validator;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

public class PlaceOfBirthValidator implements Validator<String> {
    @Override
    public void validate(String placeOfBirth) throws IncorrectEnteredDataException {
        if (placeOfBirth == null || !placeOfBirth.matches("^(\\p{L}" + //must start with a letter
                "[\\p{L}\\p{Mn}\\s'-.]{0,98}" +
                "[\\p{L}.])$")) { //must end with a letter or a dot(for cities like Washington D.C.)
            throw new IncorrectEnteredDataException("birthplaceError", "Invalid! Correct form is e.g.: Budapest");
        }
    }

}

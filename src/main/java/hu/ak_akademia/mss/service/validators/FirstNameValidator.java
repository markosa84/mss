package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.service.Validator;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

public class FirstNameValidator implements Validator<String> {

    @Override
    public void validate(String firstName) throws IncorrectEnteredDataException {
        if (firstName == null || !firstName.matches("^[A-ZÁÉÍÓÖŐÚÜŰ]([a-záéíóöőúüű]+\\s?([A-ZÁÉÍÓÖŐÚÜŰa-záéíóöőúüű]*))$")) {
            throw new IncorrectEnteredDataException("firstNameError", "Incorrect! Correct form is e.g.: Elek");
        }
        MSSUserValidatorFactory.lengthValidation(firstName);
    }

}

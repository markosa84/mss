package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

public class TAJNumberValidator implements Validator<String> {

    @Override
    public void validate(String TAJNumber) throws IncorrectEnteredDataException {
        if (TAJNumber == null || !TAJNumber.matches("^\\d{3}-\\d{3}-\\d{3}$")) { // valid TAJ e.g.: 123-456-789
            throw new IncorrectEnteredDataException("tajError", "It is Wrong! Correct form is 123-456-789");
        }
    }
}

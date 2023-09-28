package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.service.CompareValidator;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

public class ConfirmationPasswordValidator implements CompareValidator<String> {

    @Override
    public void validate(String password, String passWordAgain) throws IncorrectEnteredDataException {
        if (!password.equals(passWordAgain) ) {
            throw new IncorrectEnteredDataException("samePasswordError", "Incorrect! The two passwords must be the same");
        }
    }
}

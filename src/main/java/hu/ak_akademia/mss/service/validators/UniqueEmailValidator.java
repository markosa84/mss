package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.service.UniqueChecker;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

public class UniqueEmailValidator implements Validator<String> {

    public UniqueEmailValidator() {
    }

    @Override
    public void validate(String uniqueEmail) throws IncorrectEnteredDataException {
        if (UniqueChecker.isUniqueEmail()) {
            throw new IncorrectEnteredDataException("emailError", "This email already exists! Please choose another one.");
        }
    }
}

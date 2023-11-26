package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

public class EmailValidator implements Validator<String> {

    @Override
    public void validate(String email) throws IncorrectEnteredDataException {
        if (email == null || !email.matches("^([a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*" +
                    "@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,7})$")) {
            throw new IncorrectEnteredDataException("emailError", "Incorrect! Correct form is e.g.: teszt@mail.hu");
        }
    }

}

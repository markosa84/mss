package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.service.Validator;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;


class EmailValidator implements Validator<String> {

    @Override
    public void validate(String email) throws IncorrectEnteredDataException {
        if (email == null || !email.matches(".+@\\w+\\.[a-z]+")) {
            throw new IncorrectEnteredDataException("emailError", "Incorrect! Correct form is e.g.: teszt@mail.hu");
        }
    }

}

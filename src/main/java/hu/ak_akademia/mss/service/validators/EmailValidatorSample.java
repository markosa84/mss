package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.service.ValidatorSample;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;


class EmailValidatorSample implements ValidatorSample<Object> {

    @Override
    public void validate(Object email) throws IncorrectEnteredDataException {
        String emailText = (String) email;
        if (emailText == null || !emailText.matches(".+@\\w+\\.[a-z]+")) {
            throw new IncorrectEnteredDataException("emailError", "Incorrect! Correct form is e.g.: teszt@mail.hu");
        }
    }

}

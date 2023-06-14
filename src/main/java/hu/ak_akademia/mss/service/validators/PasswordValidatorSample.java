package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.service.ValidatorSample;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

class PasswordValidatorSample implements ValidatorSample<Object> {

    private static final String PASSWORD_REGEX = "^(?=.*[0-9])" + //Must contain at least one digit [0-9]
            "(?=.*[a-z])" + // Must contain at least one lowercase  character [a-z]
            "(?=.*[A-Z])" + //must contain at least one uppercase character [a-z]
            "(?=.*[!@#&()–[{}]:;',?/*~$^+=<>])" + // must contain at least one special character ! @ # & ( ) – [ { } ] : ; '
            // , ? / * ~ $ ^ + = < > ]
            ".{8,20}$"; // must contain a length of at least 8 characters and a maximum of 20 characters

    @Override
    public void validate(Object password) throws IncorrectEnteredDataException {
        var pass = (String) password;
        if (pass == null || !pass.matches(PASSWORD_REGEX)) {
            throw new IncorrectEnteredDataException("passwordError", "Incorrect! Must contain" +
                    " 'a' , 'A', and special character and least 8 long");
        }
    }

}

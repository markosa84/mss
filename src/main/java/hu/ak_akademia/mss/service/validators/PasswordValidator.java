package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.user.Client;
import hu.ak_akademia.mss.service.Validator;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

class PasswordValidator implements Validator<Client> {

    private static final String PASSWORD_REGEX = "^(?=.*[0-9])" + //Must contain at least one digit [0-9]
            "(?=.*[a-z])" + // Must contain at least one lowercase  character [a-z]
            "(?=.*[A-Z])" + //must contain at least one uppercase character [a-z]
            "(?=.*[!@#&()–[{}]:;',?/*~$^+=<>])" + // must contain at least one special character ! @ # & ( ) – [ { } ] : ; '
            // , ? / * ~ $ ^ + = < > ]
            ".{8,20}$"; // must contain a length of at least 8 characters and a maximum of 20 characters

    @Override
    public void validate(Client client) throws IncorrectEnteredDataException {
        if (client.getPassword() == null || !client.getPassword().matches(PASSWORD_REGEX)) {
            throw new IncorrectEnteredDataException("passwordError", "Password is not correct!");
        }
    }

}

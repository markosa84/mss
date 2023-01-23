package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.MssUsers;
import hu.ak_akademia.mss.service.Validator;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;


class EmailValidator implements Validator<MssUsers> {
//
//    @Override
//    public void validate(Client client) throws IncorrectEnteredDataException {
//        if (!client.getEmail().matches(".+@\\w+\\.[a-z]+") || client.getEmail() == null) {
//            throw new IncorrectEnteredDataException("Incorrect e-mail format!");
//        }
//    }

    @Override
    public void validate(MssUsers mssUsers) throws IncorrectEnteredDataException {
        if (mssUsers.getEmail() == null || !mssUsers.getEmail().matches(".+@\\w+\\.[a-z]+")) {
            throw new IncorrectEnteredDataException("emailError", "Incorrect email address format!");
        }
    }

//    public static void emailValidator(Client client) throws IncorrectEnteredDataException {
//        if (!client.getEmail().matches(".+@\\w+\\.[a-z]+") || client.getEmail() == null) {
//            throw new IncorrectEnteredDataException("Incorrect e-mail format!");
//        }
//    }
}

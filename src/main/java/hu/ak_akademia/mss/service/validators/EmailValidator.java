package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.Client;
import hu.ak_akademia.mss.service.Validator;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;


class EmailValidator implements Validator<Client> {
    
    @Override
    public void validate(Client client) throws IncorrectEnteredDataException {
        if (!client.getEmail().matches(".+@\\w+\\.[a-z]+") || client.getEmail() == null) {
            throw new IncorrectEnteredDataException("Incorrect e-mail format!");
        }
    }

//    public static void emailValidator(Client client) throws IncorrectEnteredDataException {
//        if (!client.getEmail().matches(".+@\\w+\\.[a-z]+") || client.getEmail() == null) {
//            throw new IncorrectEnteredDataException("Incorrect e-mail format!");
//        }
//    }
}

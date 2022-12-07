package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.Client;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;


public class EmailValidator {


    public static void emailValidator(Client client) throws IncorrectEnteredDataException {
        if (!client.getEmail().matches(".+@\\w+\\.[a-z]+") || client.getEmail() == null) {
            throw new IncorrectEnteredDataException("Incorrect e-mail format!");
        }
    }

}

package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.Client;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

public class FirstNameValidator {


    public static void firstNameValidator(Client client) throws IncorrectEnteredDataException {
        if (!client.getFirstName().matches("^[A-ZÁÉÍÓÖŐÚÜŰ]([a-záéíóöőúüű]+\\s?([A-ZÁÉÍÓÖŐÚÜŰa-záéíóöőúüű]*))$") || client.getFirstName() == null) {
            throw new IncorrectEnteredDataException("Incorrect first name format!");
        }
    }

}

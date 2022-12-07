package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.Client;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

public class NationalityValidator {

    public static void nationalityValidator(Client client) throws IncorrectEnteredDataException {
        if (!client.getNationality().matches("[a-záéíóöőúüű]+") || client.getNationality() == null) {
            throw new IncorrectEnteredDataException("Incorrect format!");
        }
    }

}

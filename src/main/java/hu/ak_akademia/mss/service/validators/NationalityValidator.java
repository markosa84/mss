package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.Client;
import hu.ak_akademia.mss.service.Validator;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

class NationalityValidator implements Validator<Client> {
    @Override
    public void validate(Client client) throws IncorrectEnteredDataException {
        if (!client.getNationality().matches("[a-záéíóöőúüű]+") || client.getNationality() == null) {
            throw new IncorrectEnteredDataException("Incorrect format!");
        }
    }

//    public static void nationalityValidator(Client client) throws IncorrectEnteredDataException {
//        if (!client.getNationality().matches("[a-záéíóöőúüű]+") || client.getNationality() == null) {
//            throw new IncorrectEnteredDataException("Incorrect format!");
//        }

//    }
}

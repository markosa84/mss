package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.Client;
import hu.ak_akademia.mss.service.Validator;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

class TAJNumberValidator implements Validator<Client> {

    @Override
    public void validate(Client client) throws IncorrectEnteredDataException {
        if (client.getTAJNumber() == null || !client.getTAJNumber().matches("^\\d{3}-\\d{3}-\\d{3}$")) { // valid TAJ e.g.: 123-456-789
            throw new IncorrectEnteredDataException("A megadott TAJ nem felel meg a formai követelményeknek!");
        }
    }

//    public void validateTajNumber(String tajNumber) throws IncorrectEnteredDataException {
//        if (tajNumber == null || !tajNumber.matches("^\\d{3}-\\d{3}-\\d{3}$")) { // valid TAJ e.g.: 123-456-789
//            throw new IncorrectEnteredDataException("A megadott TAJ nem felel meg a formai követelményeknek!");
//        }
//    }
}

package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.Client;
import hu.ak_akademia.mss.service.Validator;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

class PlaceOfBirthValidator implements Validator<Client> {
    @Override
    public void validate(Client client) throws IncorrectEnteredDataException {
        if (client.getBirthPlace() == null || !client.getBirthPlace().matches("^(\\p{L}" + //must start with a letter
                "[\\p{L}\\p{Mn}\\s'-.]{0,98}" +
                "[\\p{L}.])$")) { //must end with a letter or a dot(for cities like Washington D.C.)
            throw new IncorrectEnteredDataException("birthplaceError","The given place of birth is invalid!");
        }
    }

//    public void validatePlaceOfBirth(String placeOfBirth) throws IncorrectEnteredDataException {
//        if (placeOfBirth == null || !placeOfBirth.matches("^(\\p{L}" + //must start with a letter
//                "[\\p{L}\\p{Mn}\\s'-.]{0,98}" +
//                "[\\p{L}.])$")) { //must end with a letter or a dot(for cities like Washington D.C.)
//            throw new IncorrectEnteredDataException("A megadott születési hely érvénytelen!");
//        }
//    }
}

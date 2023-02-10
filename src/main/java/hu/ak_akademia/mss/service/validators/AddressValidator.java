package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.user.Client;
import hu.ak_akademia.mss.service.Validator;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

class AddressValidator implements Validator<Client> {

    @Override
    public void validate(Client client) throws IncorrectEnteredDataException {
        if (client.getAddress() == null || !client.getAddress().matches("^[1-9]\\d{3}" + //valid HUN postal code format
                "[\\p{L}\\d\\s-,./]{10,246}$")) {
            throw new IncorrectEnteredDataException("addressError","A megadott lakcím formátuma érvénytelen!");
        }
    }

//    public void validateAddress(String address) throws IncorrectEnteredDataException {
//        if (address == null || !address.matches("^[1-9]\\d{3}" + //valid HUN postal code format
//                "[\\p{L}\\d\\s-,./]{10,246}$")) {
//            throw new IncorrectEnteredDataException("A megadott lakcím formátuma érvénytelen!");
//        }
//    }
}

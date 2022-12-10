package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

public class AddressValidation {
    public void validateAddress(String address) throws IncorrectEnteredDataException {
        if(address == null || !address.matches("^[1-9]\\d{3}" + //valid HUN postal code format
                "[\\p{L}\\d\\s-,./]{10,246}$")){
            throw new IncorrectEnteredDataException("A megadott lakcím formátuma érvénytelen!");
        }
    }
}

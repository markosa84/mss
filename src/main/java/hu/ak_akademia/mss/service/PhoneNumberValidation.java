package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

public class PhoneNumberValidation {
    private static final String PHONE_NUMBER_REGEX = "^(06|\\+36)?" + //country code -> currently optional, delete ? to make it mandatory
            "[\\s\\-]?" + //delimiter (optional)
            "(([2,3,7]0" + //HU mobile codes
            "[\\s\\-]?" +
            "\\d{3}[\\s\\-]?\\d{4})" + //HU mobile format
            "|((1|2[2-9]|3[1-7]|4[2,4-9]|5[1-7,9]|6[2,3,6,8,9]|7[1-9]|8[0,2-5,7-9]|9[2-6,9])" + //HU dialing codes
            "[\\s\\-]?" +
            "\\d{3}[\\s\\-]?\\d{3}))$"; //HU landline format

    public void validatePhoneNumber(String phoneNumber) throws IncorrectEnteredDataException{
        if(phoneNumber == null || !phoneNumber.matches(PHONE_NUMBER_REGEX)) {
            throw new IncorrectEnteredDataException("A megadott telefonszám érvénytelen.");
        }
    }
}

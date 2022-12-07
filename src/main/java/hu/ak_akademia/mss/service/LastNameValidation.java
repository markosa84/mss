package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

public class LastNameValidation {
    private static final String LAST_NAME_REGEX = "^[A-ZÁÉÍÓÚÖÜŐŰa-záéíóúöüőű]" + //Username consists of  characters (a-zA-Z), lowercase, or uppercase
            "([. -_](?![. -_])" + // follow by a dot, hyphen, or underscore, negative lookahead to ensures dot, hyphen, and underscore does not appear consecutively
            "|[A-ZÁÉÍÓÚÖÜŐŰa-záéíóúöüőű])" +  // or  character
            "{1,29}[A-ZÁÉÍÓÚÖÜŐŰa-záéíóúöüőű]$";  // ensures the length of (group 1) between 1 and 1 end with  character

    public void lastNameValidation(String lastName) throws IncorrectEnteredDataException {
        if (lastName == null || !lastName.matches(LAST_NAME_REGEX)) {
            throw new IncorrectEnteredDataException("Nem megfelelő karakter használata.");
        }
    }
}

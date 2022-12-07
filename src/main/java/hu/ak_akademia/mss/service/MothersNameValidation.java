package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

public class MothersNameValidation {
    public void validateMothersName(String mothersName) throws IncorrectEnteredDataException {
        if(mothersName == null || !mothersName.matches("^(\\p{L}" + //must start with a letter
                "(?=\\S*\\s)" + //must contain at least one space
                "[\\p{L}\\p{Mn}\\s'-.]{3,248}"+
                "[\\p{L}.])$")) { //must end with a letter or a dot(for rare occasions when a suffix/abbrev. is used in a foreign maiden name like Jr.)
            throw new IncorrectEnteredDataException("A megadott név nem felel meg a formai követelményeknek!");
        }
    }
}

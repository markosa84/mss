package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

public class MotherNameValidator implements Validator<String> {

    @Override
    public void validate(String mothersName) throws IncorrectEnteredDataException {
        if ("Ismeretlen".equals(mothersName)) {
            return;
        }
        if (mothersName == null || !mothersName.matches("^(\\p{L}" + //must start with a letter
                "(?=\\S*\\s)" + //must contain at least one space
                "[\\p{L}\\p{Mn}\\s'-.]{3,248}" +
                "[\\p{L}.])$") //must end with a letter or a dot(for rare occasions when a suffix/abbrev. is used in a foreign maiden name like Jr.)
        ) {
            throw new IncorrectEnteredDataException("motherNameError", "The given name does not meet the formal requirements!");
        }
        MSSUserValidatorFactory.lengthValidation(mothersName);
    }

}

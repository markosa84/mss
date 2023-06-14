package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.service.ValidatorSample;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

class MotherNameValidatorSample implements ValidatorSample<Object> {

    @Override
    public void validate(Object mothersName) throws IncorrectEnteredDataException {
        var mother = (String) mothersName;
        if (mother == null || !mother.matches("^(\\p{L}" + //must start with a letter
                "(?=\\S*\\s)" + //must contain at least one space
                "[\\p{L}\\p{Mn}\\s'-.]{3,248}" +
                "[\\p{L}.])$")) { //must end with a letter or a dot(for rare occasions when a suffix/abbrev. is used in a foreign maiden name like Jr.)
            throw new IncorrectEnteredDataException("motherNameError", "The given name does not meet the formal requirements!");
        }
        MSSUserValidatorFactory.lengthValidation(mother);
    }

}

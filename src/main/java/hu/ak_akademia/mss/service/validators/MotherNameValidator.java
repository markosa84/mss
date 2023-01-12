package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.Client;
import hu.ak_akademia.mss.service.Validator;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

class MotherNameValidator implements Validator<Client> {

    @Override
    public void validate(Client client) throws IncorrectEnteredDataException {
        if (client.getMotherName() == null || !client.getMotherName().matches("^(\\p{L}" + //must start with a letter
                "(?=\\S*\\s)" + //must contain at least one space
                "[\\p{L}\\p{Mn}\\s'-.]{3,248}" +
                "[\\p{L}.])$")) { //must end with a letter or a dot(for rare occasions when a suffix/abbrev. is used in a foreign maiden name like Jr.)
            throw new IncorrectEnteredDataException("motherNameError","The given name does not meet the formal requirements!");
        }
    }

//    public void validateMothersName(String mothersName) throws IncorrectEnteredDataException {
//        if (mothersName == null || !mothersName.matches("^(\\p{L}" + //must start with a letter
//                "(?=\\S*\\s)" + //must contain at least one space
//                "[\\p{L}\\p{Mn}\\s'-.]{3,248}" +
//                "[\\p{L}.])$")) { //must end with a letter or a dot(for rare occasions when a suffix/abbrev. is used in a foreign maiden name like Jr.)
//            throw new IncorrectEnteredDataException("A megadott név nem felel meg a formai követelményeknek!");
//        }
//    }
}

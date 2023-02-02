package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.MssUser;
import hu.ak_akademia.mss.service.Validator;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

class TAJNumberValidator implements Validator<MssUser> {

    @Override
    public void validate(MssUser mssUsers) throws IncorrectEnteredDataException {
        if (mssUsers.getTAJNumber() == null || !mssUsers.getTAJNumber().matches("^\\d{3}-\\d{3}-\\d{3}$")) { // valid TAJ e.g.: 123-456-789
            throw new IncorrectEnteredDataException("tajError", "The specified TAJ does not meet the formal requirements!");
        }
    }

//    public void validateTajNumber(String tajNumber) throws IncorrectEnteredDataException {
//        if (tajNumber == null || !tajNumber.matches("^\\d{3}-\\d{3}-\\d{3}$")) { // valid TAJ e.g.: 123-456-789
//            throw new IncorrectEnteredDataException("A megadott TAJ nem felel meg a formai követelményeknek!");
//        }
//    }
}

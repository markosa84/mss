package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.MssUsers;
import hu.ak_akademia.mss.service.Validator;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

class TAJNumberValidator implements Validator<MssUsers> {

    @Override
    public void validate(MssUsers mssUsers) throws IncorrectEnteredDataException {
        if (mssUsers.getTajNumber() == null || !mssUsers.getTajNumber().matches("^\\d{3}-\\d{3}-\\d{3}$")) { // valid TAJ e.g.: 123-456-789
            throw new IncorrectEnteredDataException("tajError", "The specified TAJ does not meet the formal requirements!");
        }
    }

//    public void validateTajNumber(String tajNumber) throws IncorrectEnteredDataException {
//        if (tajNumber == null || !tajNumber.matches("^\\d{3}-\\d{3}-\\d{3}$")) { // valid TAJ e.g.: 123-456-789
//            throw new IncorrectEnteredDataException("A megadott TAJ nem felel meg a formai követelményeknek!");
//        }
//    }
}

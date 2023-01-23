package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.MssUsers;
import hu.ak_akademia.mss.service.Validator;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

import java.time.LocalDate;
import java.time.Period;

class DateOfBirthValidator implements Validator<MssUsers> {

    @Override
    public void validate(MssUsers mssUsers) throws IncorrectEnteredDataException {
        if (mssUsers.getDateOfBirth() == null) {
            throw new IncorrectEnteredDataException("dateOfBirthError", "The given date of birth is invalid!");
        }
        if (Period.between(mssUsers.getDateOfBirth(), LocalDate.now()).getYears() < 18) {
            throw new IncorrectEnteredDataException("dateOfBirthError", "Only adult clients can use the services of the clinic!");
        }
    }

//    public void validateDateOfBirth(LocalDate dateOfBirth) throws IncorrectEnteredDataException {
//        if(dateOfBirth == null) {
//            throw new IncorrectEnteredDataException("A megadott születési dátum érvénytelen!");
//        }
//        if(Period.between(dateOfBirth, LocalDate.now()).getYears() < 18) {
//            throw new IncorrectEnteredDataException("A klinika szolgáltatásait csak nagykorú kliens veheti igénybe!");
//        }
//    }
}

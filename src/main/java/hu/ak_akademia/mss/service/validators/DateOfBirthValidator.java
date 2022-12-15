package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.Client;
import hu.ak_akademia.mss.service.Validator;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

import java.time.LocalDate;
import java.time.Period;

class DateOfBirthValidator implements Validator<Client> {

    @Override
    public void validate(Client client) throws IncorrectEnteredDataException {
        if (client.getBirthday() == null) {
            throw new IncorrectEnteredDataException("A megadott születési dátum érvénytelen!");
        }
        if (Period.between(client.getBirthday(), LocalDate.now()).getYears() < 18) {
            throw new IncorrectEnteredDataException("A klinika szolgáltatásait csak nagykorú kliens veheti igénybe!");
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

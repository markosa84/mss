package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

import java.time.LocalDate;
import java.time.Period;

public class DateOfBirthValidation {
    public void validateDateOfBirth(LocalDate dateOfBirth) throws IncorrectEnteredDataException {
        if(dateOfBirth == null) {
            throw new IncorrectEnteredDataException("A megadott születési dátum érvénytelen!");
        }
        if(Period.between(dateOfBirth, LocalDate.now()).getYears() < 18) {
            throw new IncorrectEnteredDataException("A klinika szolgáltatásait csak nagykorú kliens veheti igénybe!");
        }
    }
}

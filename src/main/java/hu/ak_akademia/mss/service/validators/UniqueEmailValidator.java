package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.service.EditProfilService;
import hu.ak_akademia.mss.service.RegistrationService;
import hu.ak_akademia.mss.service.Validator;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

public class UniqueEmailValidator implements Validator<String> {

    private final RegistrationService registrationService;


    public UniqueEmailValidator(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    @Override
    public void validate(String uniqueEmail) throws IncorrectEnteredDataException {
        var g = registrationService.checkUniqueEmail(uniqueEmail);
        if (g) {
            throw new IncorrectEnteredDataException("emailError", "This email already exists! Please choose another one.");
        }
    }
}

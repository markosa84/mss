package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.service.RegistrationService;
import hu.ak_akademia.mss.service.Validator;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailValidator implements Validator<String> {

    @Autowired
    private RegistrationService registrationService;

    @Override
    public void validate(String uniqueEmail) throws IncorrectEnteredDataException {
        var g = registrationService.checkUniqueEmail(uniqueEmail);
        if (g) {
            throw new IncorrectEnteredDataException("emailError", "This email already exists! Please choose another one.");
        }
    }
}

package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.service.CompareValidator;
import hu.ak_akademia.mss.service.RegistrationService;

import java.util.HashMap;
import java.util.Map;

public class ClientCompareValidator implements CompareValidator<String> {
    private final RegistrationService registrationService;

    private final Map<String, String> validatorErrorList;

    public ClientCompareValidator(RegistrationService registrationService){
        this.registrationService = registrationService;
        this.validatorErrorList = new HashMap<>();
    }

    public ClientCompareValidator(RegistrationService registrationService, Map<String, String> validatorErrorList ){
        this.registrationService = registrationService;
        this.validatorErrorList = validatorErrorList;
    }

    @Override
    public void validate(String password, String passWordAgain) {
        var instance = MSSUserValidatorFactory.getInstance();
        instance.collectValidationError(new  ConfirmationPasswordValidator(), password, passWordAgain, validatorErrorList);
    }
}

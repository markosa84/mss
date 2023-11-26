package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.user.Assistant;
import hu.ak_akademia.mss.service.RegistrationService;

import java.util.HashMap;
import java.util.Map;

public class CompositeAssistantValidator implements Validator<Assistant> {

    private final Map<String, String> validatorErrorList = new HashMap<>();

    private final RegistrationService registrationService;

    public CompositeAssistantValidator(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public void validate(Assistant assistant) {
        var instance = MSSUserValidatorFactory.getInstance();
        instance.collectValidationError(new EmailValidator(), assistant.getEmail(), validatorErrorList);
        instance.collectValidationError(new LastNameValidator(), assistant.getLastName(), validatorErrorList);
        instance.collectValidationError(new FirstNameValidator(), assistant.getFirstName(), validatorErrorList);
        instance.collectValidationError(new PasswordValidator(), assistant.getPassword(), validatorErrorList);
        instance.collectValidationError(new PhoneNumberValidator(), assistant.getPhoneNumber(), validatorErrorList);
        instance.collectValidationError(new UniqueEmailValidator(registrationService), assistant.getEmail(), validatorErrorList);
    }

    public Map<String, String> getValidatorErrorList() {
        return validatorErrorList;
    }

}

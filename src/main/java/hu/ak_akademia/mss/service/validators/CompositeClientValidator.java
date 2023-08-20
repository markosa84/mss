package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.user.Client;
import hu.ak_akademia.mss.service.RegistrationService;
import hu.ak_akademia.mss.service.Validator;

import java.util.HashMap;
import java.util.Map;

public class CompositeClientValidator implements Validator<Client> {

    private final RegistrationService registrationService;

    private final Map<String, String> validatorErrorList = new HashMap<>();
    private final Map<String, String> validatorErrorList2 = new HashMap<>();

    public CompositeClientValidator(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public void validate(Client client) {
        var instance = MSSUserValidatorFactory.getInstance();
        instance.collectValidationError(new EmailValidator(), client.getEmail(), validatorErrorList);
        instance.collectValidationError(new LastNameValidator(), client.getLastName(), validatorErrorList);
        instance.collectValidationError(new FirstNameValidator(), client.getFirstName(), validatorErrorList);
        instance.collectValidationError(new PasswordValidator(), client.getPassword(), validatorErrorList);
        instance.collectValidationError(new PhoneNumberValidator(), client.getPhoneNumber(), validatorErrorList);
        instance.collectValidationError(new MotherNameValidator(), client.getMothersName(), validatorErrorList);
        instance.collectValidationError(new PlaceOfBirthValidator(), client.getPlaceOfBirth(), validatorErrorList);
        instance.collectValidationError(new DateOfBirthValidator(), client.getDateOfBirth(), validatorErrorList);
        instance.collectValidationError(new TAJNumberValidator(), client.getTAJNumber(), validatorErrorList);
        if (client.getUserId() == 0){
        instance.collectValidationError(new UniqueEmailValidator(registrationService), client.getEmail(), validatorErrorList);
        }
    }

    public Map<String, String> getValidatorErrorList() {
        return validatorErrorList;
    }

    public Map<String, String> getValidatorErrorList2() {
        return validatorErrorList2;
    }

}

package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.user.Client;
import hu.ak_akademia.mss.repository.MSSUserRepository;

import java.util.HashMap;
import java.util.Map;

public class CompositeClientValidator implements Validator<Client> {

    private final Map<String, String> validatorErrorList = new HashMap<>();

    private final MSSUserRepository mssUserRepository;

    public CompositeClientValidator(MSSUserRepository mssUserRepository) {
        this.mssUserRepository = mssUserRepository;
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
        instance.collectValidationError(new UniqueEmailValidator(mssUserRepository), client.getEmail(), validatorErrorList);
    }

    public Map<String, String> getValidatorErrorList() {
        return validatorErrorList;
    }

}

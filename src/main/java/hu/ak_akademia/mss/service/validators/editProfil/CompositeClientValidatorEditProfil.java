package hu.ak_akademia.mss.service.validators.editProfil;

import hu.ak_akademia.mss.model.user.Client;
import hu.ak_akademia.mss.service.EditProfilService;
import hu.ak_akademia.mss.service.validators.Validator;
import hu.ak_akademia.mss.service.validators.*;

import java.util.HashMap;
import java.util.Map;

public class CompositeClientValidatorEditProfil implements Validator<Client> {

    private final EditProfilService editProfilService;

    private final Map<String, String> validatorErrorList = new HashMap<>();

    public CompositeClientValidatorEditProfil(EditProfilService editProfilService) {
        this.editProfilService = editProfilService;
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

    }

    public Map<String, String> getValidatorErrorList() {
        return validatorErrorList;
    }


}

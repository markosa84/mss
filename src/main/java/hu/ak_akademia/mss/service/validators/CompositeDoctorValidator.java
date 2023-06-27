package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.user.Doctor;
import hu.ak_akademia.mss.service.RegistrationService;
import hu.ak_akademia.mss.service.Validator;

import java.util.HashMap;
import java.util.Map;

public class CompositeDoctorValidator implements Validator<Doctor> {

    private final RegistrationService registrationService;

    private final Map<String, String> validatorErrorList = new HashMap<>();

    public CompositeDoctorValidator(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public void validate(Doctor doctor) {
        var instance = MSSUserValidatorFactory.getInstance();
        instance.collectValidationError(new EmailValidator(), doctor.getEmail(), validatorErrorList);
        instance.collectValidationError(new LastNameValidator(), doctor.getLastName(), validatorErrorList);
        instance.collectValidationError(new FirstNameValidator(), doctor.getFirstName(), validatorErrorList);
        instance.collectValidationError(new PasswordValidator(), doctor.getPassword(), validatorErrorList);
        instance.collectValidationError(new PhoneNumberValidator(), doctor.getPhoneNumber(), validatorErrorList);
        instance.collectValidationError(new UniqueEmailValidator(registrationService), doctor.getEmail(), validatorErrorList);
    }

    public Map<String, String> getValidatorErrorList() {
        return validatorErrorList;
    }

}

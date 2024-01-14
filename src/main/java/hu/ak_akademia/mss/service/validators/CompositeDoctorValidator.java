package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.user.Doctor;
import hu.ak_akademia.mss.repository.MSSUserRepository;

import java.util.HashMap;
import java.util.Map;

public class CompositeDoctorValidator implements Validator<Doctor> {

    private final Map<String, String> validatorErrorList = new HashMap<>();

    private final MSSUserRepository mssUserRepository;

    public CompositeDoctorValidator(MSSUserRepository mssUserRepository) {
        this.mssUserRepository = mssUserRepository;
    }

    @Override
    public void validate(Doctor doctor) {
        var instance = MSSUserValidatorFactory.getInstance();
        instance.collectValidationError(new EmailValidator(), doctor.getEmail(), validatorErrorList);
        instance.collectValidationError(new LastNameValidator(), doctor.getLastName(), validatorErrorList);
        instance.collectValidationError(new FirstNameValidator(), doctor.getFirstName(), validatorErrorList);
        instance.collectValidationError(new PasswordValidator(), doctor.getPassword(), validatorErrorList);
        instance.collectValidationError(new PhoneNumberValidator(), doctor.getPhoneNumber(), validatorErrorList);
        instance.collectValidationError(new UniqueEmailValidator(mssUserRepository), doctor.getEmail(), validatorErrorList);
    }

    public Map<String, String> getValidatorErrorList() {
        return validatorErrorList;
    }

}

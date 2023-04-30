package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.user.Client;
import hu.ak_akademia.mss.model.user.Doctor;
import hu.ak_akademia.mss.service.Validator;

import java.util.List;

public class MSSUserValidatorFactory {

    private static final MSSUserValidatorFactory INSTANCE = new MSSUserValidatorFactory();

    private MSSUserValidatorFactory() {
    }

    public static MSSUserValidatorFactory getInstance() {
        return INSTANCE;
    }

    private static final List<Validator<Client>> LIST = List.of( //
            new EmailValidator(), //
            new FirstNameValidator(), //
            new LastNameValidator(), //
            new PasswordValidator(), //
            new PhoneNumberValidator(), //
            new MotherNameValidator(), //
            new PlaceOfBirthValidator(), //
            new DateOfBirthValidator(), //
            new TAJNumberValidator());

    public List<Validator<Client>> getAllClientValidators() {
        return LIST;
    }

    public List<Validator<Doctor>> getAllDoctorValidators() {
        return List.of( //
                new DoctorEmailValidator(), //
                new DoctorFirstNameValidator(), //
                new DoctorLastNameValidator(), //
                new DoctorPasswordValidator(), //
                new DoctorPhoneNumberValidator() //
        );
    }

}

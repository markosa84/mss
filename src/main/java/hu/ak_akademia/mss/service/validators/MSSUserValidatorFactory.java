package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.user.MssUser;
import hu.ak_akademia.mss.service.Validator;

import java.util.List;

public class MSSUserValidatorFactory {

    private static final MSSUserValidatorFactory INSTANCE = new MSSUserValidatorFactory();

    private MSSUserValidatorFactory() {
    }

    public static MSSUserValidatorFactory getInstance() {
        return INSTANCE;
    }

    private static List<Validator<MssUser>> LIST = List.of( //
            new DateOfBirthValidator(), //
//            new AddressValidator(), //
//            new GenderValidator(), //
            new EmailValidator(), //
            new FirstNameValidator(), //
            new LastNameValidator(), //
            new MotherNameValidator(), //
//            new NationalityValidator(), //
            new PasswordValidator(), //
//            new PhoneNumberValidator(), //
            new PlaceOfBirthValidator(), //
            new TAJNumberValidator()
    ); //

    public List<Validator<MssUser>> getAllMSSUserValidators() {
        return LIST;
    }
}

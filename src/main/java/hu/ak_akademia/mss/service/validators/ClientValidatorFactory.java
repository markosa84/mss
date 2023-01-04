package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.Client;
import hu.ak_akademia.mss.service.Validator;

import java.util.List;

public class ClientValidatorFactory {

    private static final ClientValidatorFactory INSTANCE = new ClientValidatorFactory();

    private ClientValidatorFactory() {
    }

    public static ClientValidatorFactory getInstance() {
        return INSTANCE;
    }

    private static List<Validator<Client>> LIST = List.of( //
//            new AddressValidator(), //
            new DateOfBirthValidator(), //
//            new GenderValidator(), //
//            new EmailValidator(), //
//            new FirstNameValidator(), //
//            new LastNameValidator() //
//            new MotherNameValidator(), //
//            new NationalityValidator(), //
            new PasswordValidator() //
//            new PhoneNumberValidator(), //
//            new PlaceOfBirthValidator(), //
//            new TAJNumberValidator()
    ); //

    public List<Validator<Client>> getAllClientValidators() {
        return LIST;
    }
}

package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.service.Validator;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

import java.util.List;
import java.util.Map;

public class MSSUserValidatorFactory {

    private static final MSSUserValidatorFactory INSTANCE = new MSSUserValidatorFactory();

    private MSSUserValidatorFactory() {
    }

    public static MSSUserValidatorFactory getInstance() {
        return INSTANCE;
    }

    public static void lengthValidation(String text) throws IncorrectEnteredDataException {
        if (text.length() > 100) {
            throw new IncorrectEnteredDataException("firstNameError", "Too much character!");
        }
    }

    public <T> void collectValidationError(Validator<T> validator, T mssUser, Map<String, String> errorList) {
        try {
            validator.validate(mssUser);
        } catch (IncorrectEnteredDataException e) {
            errorList.put(e.getMessage(), e.getErrorMessage());
        }
    }

    public List<Validator<String>> getValidators() {
        return List.of(new PhoneNumberValidator(), new EmailValidator());
    }
}

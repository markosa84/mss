package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.user.Assistant;
import hu.ak_akademia.mss.model.user.Doctor;
import hu.ak_akademia.mss.model.user.FinancialColleague;
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

    public List<Validator<Doctor>> getAllDoctorValidators() {
        return List.of( //
                new DoctorEmailValidator(), //
                new DoctorFirstNameValidator(), //
                new DoctorLastNameValidator(), //
                new DoctorPasswordValidator(), //
                new DoctorPhoneNumberValidator() //
        );
    }

    public List<Validator<Assistant>> getAllAssistantValidators() {
        return List.of( //
                new AssistantFirstNameValidator(), //
                new AssistantLastNameValidator(), //
                new AssistantEmailValidator(), //
                new AssistantPasswordValidator(), //
                new AssistantPhoneNumberValidator()
        );
    }

    public static void lengthValidation(String text) throws IncorrectEnteredDataException {
        if (text.length() > 100) {
            throw new IncorrectEnteredDataException("firstNameError", "Too much character!");
        }
    }

    public List<Validator<FinancialColleague>> getAllColleagueValidators() {
        return List.of( //
                new ColleagueFirstNameValidator(), //
                new ColleagueLastNameValidator(), //
                new ColleagueEmailValidator(), //
                new ColleaguePasswordValidator(), //
                new ColleaguePhoneNumberValidator()
        );
    }

    public <T> void collectValidationError(Validator<T> validator, T mssUser, Map<String, String> errorList) {
        try {
            validator.validate(mssUser);
        } catch (IncorrectEnteredDataException e) {
            errorList.put(e.getMessage(), e.getErrorMessage());
        }
    }
}

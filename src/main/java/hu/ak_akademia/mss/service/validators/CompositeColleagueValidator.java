package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.user.FinancialColleague;
import hu.ak_akademia.mss.repository.MSSUserRepository;

import java.util.HashMap;
import java.util.Map;

public class CompositeColleagueValidator implements Validator<FinancialColleague> {


    private final Map<String, String> validatorErrorList = new HashMap<>();

    private final MSSUserRepository mssUserRepository;

    public CompositeColleagueValidator(MSSUserRepository mssUserRepository) {
        this.mssUserRepository = mssUserRepository;
    }

    @Override
    public void validate(FinancialColleague financialColleague) {
        var instance = MSSUserValidatorFactory.getInstance();
        instance.collectValidationError(new EmailValidator(), financialColleague.getEmail(), validatorErrorList);
        instance.collectValidationError(new LastNameValidator(), financialColleague.getLastName(), validatorErrorList);
        instance.collectValidationError(new FirstNameValidator(), financialColleague.getFirstName(), validatorErrorList);
        instance.collectValidationError(new PasswordValidator(), financialColleague.getPassword(), validatorErrorList);
        instance.collectValidationError(new PhoneNumberValidator(), financialColleague.getPhoneNumber(), validatorErrorList);
        instance.collectValidationError(new UniqueEmailValidator(mssUserRepository), financialColleague.getEmail(), validatorErrorList);
    }

    public Map<String, String> getValidatorErrorList() {
        return validatorErrorList;
    }

}

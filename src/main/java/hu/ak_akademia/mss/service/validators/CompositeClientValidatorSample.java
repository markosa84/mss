package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.user.Client;
import hu.ak_akademia.mss.service.Validator;
import hu.ak_akademia.mss.service.ValidatorSample;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CompositeClientValidatorSample implements Validator<Client> {

    private final List<ValidationPair> validators = List.of(
            new ValidationPair(new EmailValidatorSample(), Client::getEmail), //
            new ValidationPair(new FirstNameValidatorSample(), Client::getFirstName), //
            new ValidationPair(new LastNameValidatorSample(), Client::getLastName), //
            new ValidationPair(new PasswordValidatorSample(), Client::getPassword), //
            new ValidationPair(new PlaceOfBirthValidatorSample(), Client::getPlaceOfBirth), //
            new ValidationPair(new MotherNameValidatorSample(), Client::getMothersName), //
            new ValidationPair(new PhoneNumberValidatorSample(), Client::getPhoneNumber)
    );

    private final Map<String, String> validatorErrorList = new HashMap<>();

    @Override
    public void validate(Client client) {
        for (ValidationPair v : validators) {
            try {
                v.validator.validate(v.valueGetter.apply(client));
            } catch (IncorrectEnteredDataException e) {
                validatorErrorList.put(e.getMessage(), e.getErrorMessage());
            }
        }
    }

    public Map<String, String> getValidatorErrorList() {
        return validatorErrorList;
    }

    static class ValidationPair {

        ValidatorSample<?> validator;
        Function<Client, Object> valueGetter;

        public ValidationPair(ValidatorSample<?> validator, Function<Client, Object> valueGetter) {
            this.validator = validator;
            this.valueGetter = valueGetter;
        }

    }
}

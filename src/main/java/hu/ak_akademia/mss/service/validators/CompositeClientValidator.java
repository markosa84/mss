package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.user.Client;
import hu.ak_akademia.mss.service.Validator;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompositeClientValidator implements Validator<Client> {

    private final List<Validator<Client>> validators = new ArrayList<>();
    private final Map<String, String> validatorErrorList = new HashMap<>();

    @Override
    public void validate(Client client) {
        for (var v : validators) {
            try {
                v.validate(client);
            } catch (IncorrectEnteredDataException e) {
                validatorErrorList.put(e.getMessage(), e.getErrorMessage());
            }
        }
    }

    public Map<String, String> getValidatorErrorList() {
        return validatorErrorList;
    }

    public void addValidators(List<Validator<Client>> validator) {
        validators.addAll(validator);
    }


}

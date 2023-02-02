package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.MssUser;
import hu.ak_akademia.mss.service.Validator;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompositeMSSUserValidator implements Validator<MssUser> {

    private final List<Validator<MssUser>> validators = new ArrayList<>();
    private final Map<String, String> validatorErrorList = new HashMap<>();

    @Override
    public void validate(MssUser mssUsers) {
        for (var v : validators) {
            try {
                v.validate(mssUsers);
            } catch (IncorrectEnteredDataException e) {
                validatorErrorList.put(e.getMessage(), e.getErrorMessage());
            }
        }
    }

    public Map<String, String> getValidatorErrorList() {
        return validatorErrorList;
    }

    public void addValidators(List<Validator<MssUser>> validator) {
        validators.addAll(validator);
    }


}

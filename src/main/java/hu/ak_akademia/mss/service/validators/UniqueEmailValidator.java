package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.repository.MSSUserRepository;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

public class UniqueEmailValidator implements Validator<String> {

    private final MSSUserRepository mssUserRepository;

    public UniqueEmailValidator(MSSUserRepository mssUserRepository) {
        this.mssUserRepository = mssUserRepository;
    }

    @Override
    public void validate(String uniqueEmail) throws IncorrectEnteredDataException {
//        if (u) {
        if (inspectionUniqueEmail(uniqueEmail)) {
            throw new IncorrectEnteredDataException("emailError", "This email already exists! Please choose another one.");
        }
    }

    private boolean inspectionUniqueEmail(String email) {
        return mssUserRepository.findByEmail(email).isPresent();
    }


}

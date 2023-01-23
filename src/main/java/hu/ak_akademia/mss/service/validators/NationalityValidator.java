package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.MssUsers;
import hu.ak_akademia.mss.service.Validator;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

class NationalityValidator implements Validator<MssUsers> {
    @Override
    public void validate(MssUsers mssUsers) throws IncorrectEnteredDataException {
        if (mssUsers.getNationality() == null || !mssUsers.getNationality().matches("[a-záéíóöőúüű]+")) {
            throw new IncorrectEnteredDataException("Incorrect format!");
        }
    }

//    public static void nationalityValidator(Client client) throws IncorrectEnteredDataException {
//        if (!client.getNationality().matches("[a-záéíóöőúüű]+") || client.getNationality() == null) {
//            throw new IncorrectEnteredDataException("Incorrect format!");
//        }

//    }
}

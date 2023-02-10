package hu.ak_akademia.mss.service.validators;

import hu.ak_akademia.mss.model.user.MssUser;
import hu.ak_akademia.mss.service.Validator;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

class FirstNameValidator implements Validator<MssUser> {

    @Override
    public void validate(MssUser mssUsers) throws IncorrectEnteredDataException {
        if (mssUsers.getFirstName() == null || !mssUsers.getFirstName().matches("^[A-ZÁÉÍÓÖŐÚÜŰ]([a-záéíóöőúüű]+\\s?([A-ZÁÉÍÓÖŐÚÜŰa-záéíóöőúüű]*))$")) {
            throw new IncorrectEnteredDataException("firstNameError", "Incorrect first name format!");
        }
    }


    //    public static void firstNameValidator(Client client) throws IncorrectEnteredDataException {
//        if (!client.getFirstName().matches("^[A-ZÁÉÍÓÖŐÚÜŰ]([a-záéíóöőúüű]+\\s?([A-ZÁÉÍÓÖŐÚÜŰa-záéíóöőúüű]*))$") || client.getFirstName() == null) {
//            throw new IncorrectEnteredDataException("Incorrect first name format!");
//        }
//    }
}

//package hu.ak_akademia.mss.service.validators;
//
//import hu.ak_akademia.mss.model.Client;
//import hu.ak_akademia.mss.model.MssUsers;
//import hu.ak_akademia.mss.service.Validator;
//import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;
//
//public class GenderValidator implements Validator<MssUsers> {
//
//    public void validate(MssUsers mssUsers) throws IncorrectEnteredDataException {
//        if (mssUsers.getGender() == null || mssUsers.getGender().matches("^[A-ZÁÉÍÓÖŐÚÜŰ]([a-záéíóöőúüű]+\\s?([A-ZÁÉÍÓÖŐÚÜŰa-záéíóöőúüű]*))$")) {
//            throw new IncorrectEnteredDataException("genderError", "Incorrect gender format!");
//        }
//
//    }
//
//
//}

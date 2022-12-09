package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.Client;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;

import java.time.LocalDate;
import java.time.Period;

public class Validator {
    private static final String PHONE_NUMBER_REGEX = "^(06|\\+36)?" + //country code -> currently optional, delete ? to make it mandatory
            "[\\s\\-]?" + //delimiter (optional)
            "(([2,3,7]0" + //HU mobile codes
            "[\\s\\-]?" +
            "\\d{3}[\\s\\-]?\\d{4})" + //HU mobile format
            "|((1|2[2-9]|3[1-7]|4[2,4-9]|5[1-7,9]|6[2,3,6,8,9]|7[1-9]|8[0,2-5,7-9]|9[2-6,9])" + //HU dialing codes
            "[\\s\\-]?" +
            "\\d{3}[\\s\\-]?\\d{3}))$"; //HU landline format

    private static final String LAST_NAME_REGEX = "^[A-ZÁÉÍÓÚÖÜŐŰa-záéíóúöüőű]" + //Username consists of  characters (a-zA-Z), lowercase, or uppercase
            "([. -_](?![. -_])" + // follow by a dot, hyphen, or underscore, negative lookahead to ensures dot, hyphen, and underscore does not appear consecutively
            "|[A-ZÁÉÍÓÚÖÜŐŰa-záéíóúöüőű])" +  // or  character
            "{1,29}[A-ZÁÉÍÓÚÖÜŐŰa-záéíóúöüőű]$";  // ensures the length of (group 1) between 1 and 1 end with character

    private static final String PASSWORD_REGEX = "^(?=.*[0-9])" + //Must contain at least one digit [0-9]
            "(?=.*[a-z])" + // Must contain at least one lowercase  character [a-z]
            "(?=.*[A-Z])" + //must contain at least one uppercase character [a-z]
            "(?=.*[!@#&()–[{}]:;',?/*~$^+=<>])" + // must contain at least one special character ! @ # & ( ) – [ { } ] : ; '
            // , ? / * ~ $ ^ + = < > ]
            ".{8,20}$"; // must contain a length of at least 8 characters and a maximum of 20 characters

    public void process(Client client) throws IncorrectEnteredDataException {
        validatePhoneNumber(client);
        validateLastName(client);
        validatePassword(client);
        emailValidator(client);
        firstNameValidator(client);
        nationalityValidator(client);
        validateTajNumber(client);
        validatePlaceOfBirth(client);
        validateDateOfBirth(client);
        validateMothersName(client);
    }

    private void validatePhoneNumber(Client client) throws IncorrectEnteredDataException {
        if (client.getPhoneNumber() == null || !client.getPhoneNumber().matches(PHONE_NUMBER_REGEX)) {
            throw new IncorrectEnteredDataException("A megadott telefonszám érvénytelen.");
        }
    }

    private void validateLastName(Client client) throws IncorrectEnteredDataException {
        if (client.getLastName() == null || !client.getLastName().matches(LAST_NAME_REGEX)) {
            throw new IncorrectEnteredDataException("Nem megfelelő karakter használata.");
        }
    }

    private void validatePassword(Client client) throws IncorrectEnteredDataException {
        if (client.getPassword() == null || !client.getPassword().matches(PASSWORD_REGEX)) {
            throw new IncorrectEnteredDataException("A megadott jelszó nem megfelelő.");
        }
    }

    private void emailValidator(Client client) throws IncorrectEnteredDataException {
        if (!client.getEmail().matches(".+@\\w+\\.[a-z]+") || client.getEmail() == null) {
            throw new IncorrectEnteredDataException("Incorrect e-mail format!");
        }
    }

    private void firstNameValidator(Client client) throws IncorrectEnteredDataException {
        if (!client.getFirstName().matches("^[A-ZÁÉÍÓÖŐÚÜŰ]([a-záéíóöőúüű]+\\s?([A-ZÁÉÍÓÖŐÚÜŰa-záéíóöőúüű]*))$") || client.getFirstName() == null) {
            throw new IncorrectEnteredDataException("Incorrect first name format!");
        }
    }

    private void nationalityValidator(Client client) throws IncorrectEnteredDataException {
        if (!client.getNationality().matches("[a-záéíóöőúüű]+") || client.getNationality() == null) {
            throw new IncorrectEnteredDataException("Incorrect format!");
        }
    }

    private void validateTajNumber(Client client) throws IncorrectEnteredDataException {
        if (client.getTAJNumber() == null || !client.getTAJNumber().matches("^\\d{3}-\\d{3}-\\d{3}$")) { // valid TAJ e.g.: 123-456-789
            throw new IncorrectEnteredDataException("A megadott TAJ nem felel meg a formai követelményeknek!");
        }
    }

    private void validatePlaceOfBirth(Client client) throws IncorrectEnteredDataException {
        if (client.getBirthPlace() == null || !client.getBirthPlace().matches("^(\\p{L}" + //must start with a letter
                "[\\p{L}\\p{Mn}\\s'-.]{0,98}" +
                "[\\p{L}.])$")) { //must end with a letter or a dot(for cities like Washington D.C.)
            throw new IncorrectEnteredDataException("A megadott születési hely érvénytelen!");
        }
    }

    private void validateDateOfBirth(Client client) throws IncorrectEnteredDataException {
        if (client.getBirthday() == null) {
            throw new IncorrectEnteredDataException("A megadott születési dátum érvénytelen!");
        }
        if (Period.between(client.getBirthday(), LocalDate.now()).getYears() < 18) {
            throw new IncorrectEnteredDataException("A klinika szolgáltatásait csak nagykorú kliens veheti igénybe!");
        }
    }

    private void validateMothersName(Client client) throws IncorrectEnteredDataException {
        if (client.getMotherName() == null || !client.getMotherName().matches("^(\\p{L}" + //must start with a letter
                "(?=\\S*\\s)" + //must contain at least one space
                "[\\p{L}\\p{Mn}\\s'-.]{3,248}" +
                "[\\p{L}.])$")) { //must end with a letter or a dot(for rare occasions when a suffix/abbrev. is used in a foreign maiden name like Jr.)
            throw new IncorrectEnteredDataException("A megadott név nem felel meg a formai követelményeknek!");
        }
    }

}

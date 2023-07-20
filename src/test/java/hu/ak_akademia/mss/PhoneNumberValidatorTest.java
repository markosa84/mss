package hu.ak_akademia.mss;

import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;
import hu.ak_akademia.mss.service.validators.MSSUserValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PhoneNumberValidatorTest {

    @Test
    void phoneNumberValidator() {
        var phoneTest = MSSUserValidatorFactory.getInstance().getValidators().get(0);
        Assertions.assertThrows(IncorrectEnteredDataException.class, () -> phoneTest.validate(""));
        Assertions.assertThrows(IncorrectEnteredDataException.class, () -> phoneTest.validate(",.-"));
        Assertions.assertThrows(IncorrectEnteredDataException.class, () -> phoneTest.validate(",.-mhz"));
        Assertions.assertThrows(IncorrectEnteredDataException.class, () -> phoneTest.validate("+3630123456"));
        Assertions.assertThrows(IncorrectEnteredDataException.class, () -> phoneTest.validate("06130123456"));
        Assertions.assertThrows(IncorrectEnteredDataException.class, () -> phoneTest.validate("+3630123456"));
        Assertions.assertDoesNotThrow(() -> phoneTest.validate("+36301234567"));
        Assertions.assertDoesNotThrow(() -> phoneTest.validate("+36309876543"));
    }
}

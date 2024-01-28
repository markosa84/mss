package hu.ak_akademia.mss.dto;

import java.time.LocalDate;
import java.util.List;

public interface MssUserDto {

    String getEmail();

    String getPassword();

    String getFirstName();

    String getLastName();

    String getPhoneNumber();

    default List<Integer> getLanguageId() {
        return null;
    }

    default Integer getGenderId() {
        return null;
    }

    default LocalDate getDateOfBirth() {
        return null;
    }

    default String getMothersName() {
        return null;
    }

    default String getPlaceOfBirth() {
        return null;
    }

    default String getTajNumber() {
        return null;
    }

    default List<Integer> getAreaOfExpertiseId() {
        return null;
    }
}

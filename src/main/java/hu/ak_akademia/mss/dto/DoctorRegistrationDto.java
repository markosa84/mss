package hu.ak_akademia.mss.dto;

import java.io.Serializable;
import java.util.List;

public class DoctorRegistrationDto implements Serializable, MssUserDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int genderId;
    private String phoneNumber;
    private List<Integer> languageId;
    private List<Integer> areaOfExpertiseId;

    public DoctorRegistrationDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Integer> getLanguageId() {
        return languageId;
    }

    public void setLanguageId(List<Integer> languageId) {
        this.languageId = languageId;
    }

    public List<Integer> getAreaOfExpertiseId() {
        return areaOfExpertiseId;
    }

    public void setAreaOfExpertiseId(List<Integer> areaOfExpertiseId) {
        this.areaOfExpertiseId = areaOfExpertiseId;
    }

    @Override
    public String toString() {
        return "DoctorRegistrationDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", genderId=" + genderId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", languageId=" + languageId +
                ", areaOfExpertiseId=" + areaOfExpertiseId +
                '}';
    }

}

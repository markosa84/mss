package hu.ak_akademia.mss.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.StringJoiner;

public class ClientRegistrationDto implements Serializable {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int genderId;
    private String phoneNumber;
    private List<Integer> languageId;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private String mothersName;
    private String TAJNumber;


    public ClientRegistrationDto() {
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

    public void setGenderId(Integer genderId) {
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public String getTAJNumber() {
        return TAJNumber;
    }

    public void setTAJNumber(String TAJNumber) {
        this.TAJNumber = TAJNumber;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ClientRegistrationDto.class.getSimpleName() + "[", "]")
                .add("email='" + email + "'")
                .add("password='" + password + "'")
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("genderId=" + genderId)
                .add("phoneNumber='" + phoneNumber + "'")
                .add("languageId=" + languageId)
                .add("dateOfBirth=" + dateOfBirth)
                .add("placeOfBirth='" + placeOfBirth + "'")
                .add("mothersName='" + mothersName + "'")
                .add("TAJNumber='" + TAJNumber + "'")
                .toString();
    }
}

package hu.ak_akademia.mss.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class MssUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private boolean active;  // automatically true value
    private LocalDate registrationDate = LocalDate.now(); // automatically LocalDate.now() value
    private String email;
    private String password;
    private int userTypeId;
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private String mothersName;
    private String tajNumber;
    private int genderId;
    private String nationality;
    private String preferableLanguage;
    private String address;
    private int financialBalanceHuf = 0;  // start with 0 value
    private String areaOfExpertise;
    private String phoneNumber;

    public MssUsers() {
    }

    public MssUsers(int userId, boolean active, LocalDate registrationDate, String email, String password, int userTypeId, String firstName, String lastName, LocalDate dateOfBirth, String placeOfBirth, String mothersName, String tajNumber, int genderId, String nationality, String preferableLanguage, String address, int financialBalanceHuf, String areaOfExpertise, String phoneNumber) {
        this.userId = userId;
        this.active = active;
        this.registrationDate = registrationDate;
        this.email = email;
        this.password = password;
        this.userTypeId = userTypeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.mothersName = mothersName;
        this.tajNumber = tajNumber;
        this.genderId = genderId;
        this.nationality = nationality;
        this.preferableLanguage = preferableLanguage;
        this.address = address;
        this.financialBalanceHuf = financialBalanceHuf;
        this.areaOfExpertise = areaOfExpertise;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "MssUsers{" +
                "userId=" + userId +
                ", active=" + active +
                ", registrationDate=" + registrationDate +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userTypeId=" + userTypeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", mothersName='" + mothersName + '\'' +
                ", tajNumber='" + tajNumber + '\'' +
                ", genderId=" + genderId +
                ", nationality='" + nationality + '\'' +
                ", preferableLanguage='" + preferableLanguage + '\'' +
                ", address='" + address + '\'' +
                ", financialBalanceHuf=" + financialBalanceHuf +
                ", areaOfExpertise='" + areaOfExpertise + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MssUsers mssUsers = (MssUsers) o;
        return userId == mssUsers.userId && active == mssUsers.active && userTypeId == mssUsers.userTypeId && genderId == mssUsers.genderId && financialBalanceHuf == mssUsers.financialBalanceHuf && Objects.equals(registrationDate, mssUsers.registrationDate) && Objects.equals(email, mssUsers.email) && Objects.equals(password, mssUsers.password) && Objects.equals(firstName, mssUsers.firstName) && Objects.equals(lastName, mssUsers.lastName) && Objects.equals(dateOfBirth, mssUsers.dateOfBirth) && Objects.equals(placeOfBirth, mssUsers.placeOfBirth) && Objects.equals(mothersName, mssUsers.mothersName) && Objects.equals(tajNumber, mssUsers.tajNumber) && Objects.equals(nationality, mssUsers.nationality) && Objects.equals(preferableLanguage, mssUsers.preferableLanguage) && Objects.equals(address, mssUsers.address) && Objects.equals(areaOfExpertise, mssUsers.areaOfExpertise) && Objects.equals(phoneNumber, mssUsers.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, active, registrationDate, email, password, userTypeId, firstName, lastName, dateOfBirth, placeOfBirth, mothersName, tajNumber, genderId, nationality, preferableLanguage, address, financialBalanceHuf, areaOfExpertise, phoneNumber);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
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

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
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

    public String getTajNumber() {
        return tajNumber;
    }

    public void setTajNumber(String tajNumber) {
        this.tajNumber = tajNumber;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPreferableLanguage() {
        return preferableLanguage;
    }

    public void setPreferableLanguage(String preferableLanguage) {
        this.preferableLanguage = preferableLanguage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFinancialBalanceHuf() {
        return financialBalanceHuf;
    }

    public void setFinancialBalanceHuf(int financialBalanceHuf) {
        this.financialBalanceHuf = financialBalanceHuf;
    }

    public String getAreaOfExpertise() {
        return areaOfExpertise;
    }

    public void setAreaOfExpertise(String areaOfExpertise) {
        this.areaOfExpertise = areaOfExpertise;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}



package hu.ak_akademia.mss.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class MssUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private boolean active;
    private LocalDate registrationDate;
    private String email;
    private String password;
    private int userTypeId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private String mothersName;
    private String tajNumber;
    private int genderId;
    private String nationality;
    private String preferableLanguage;
    private String address;
    private int financialBalanceHuf;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MssUsers mss_users = (MssUsers) o;

        if (userId != mss_users.userId) return false;
        if (active != mss_users.active) return false;
        if (userTypeId != mss_users.userTypeId) return false;
        if (genderId != mss_users.genderId) return false;
        if (financialBalanceHuf != mss_users.financialBalanceHuf) return false;
        if (!Objects.equals(registrationDate, mss_users.registrationDate))
            return false;
        if (!Objects.equals(email, mss_users.email)) return false;
        if (!Objects.equals(password, mss_users.password)) return false;
        if (!Objects.equals(firstName, mss_users.firstName)) return false;
        if (!Objects.equals(lastName, mss_users.lastName)) return false;
        if (!Objects.equals(dateOfBirth, mss_users.dateOfBirth))
            return false;
        if (!Objects.equals(placeOfBirth, mss_users.placeOfBirth))
            return false;
        if (!Objects.equals(mothersName, mss_users.mothersName))
            return false;
        if (!Objects.equals(tajNumber, mss_users.tajNumber)) return false;
        if (!Objects.equals(nationality, mss_users.nationality))
            return false;
        if (!Objects.equals(preferableLanguage, mss_users.preferableLanguage))
            return false;
        if (!Objects.equals(address, mss_users.address)) return false;
        if (!Objects.equals(areaOfExpertise, mss_users.areaOfExpertise))
            return false;
        return Objects.equals(phoneNumber, mss_users.phoneNumber);
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + userTypeId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (placeOfBirth != null ? placeOfBirth.hashCode() : 0);
        result = 31 * result + (mothersName != null ? mothersName.hashCode() : 0);
        result = 31 * result + (tajNumber != null ? tajNumber.hashCode() : 0);
        result = 31 * result + genderId;
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + (preferableLanguage != null ? preferableLanguage.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + financialBalanceHuf;
        result = 31 * result + (areaOfExpertise != null ? areaOfExpertise.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Mss_users{" +
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
}



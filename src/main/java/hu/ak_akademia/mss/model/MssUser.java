package hu.ak_akademia.mss.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class MssUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private boolean active;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(updatable = false)
    private LocalDate registrationDate = LocalDate.now(); // automatically LocalDate.now() value
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String userTypeId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    private String placeOfBirth;

    @Column(nullable = false)
    private String mothersName;

    @Column(length = 11)
    private String TAJNumber;
    @Column(nullable = false)
    private String gender;
    //    private String nationality;
//    private String preferableLanguage;
    @Column(nullable = false)
    private String address;
    //    private int financialBalanceHuf = 0;  // start with 0 value
//    private String areaOfExpertise;
    @Column(nullable = false)
    private String phoneNumber;

    public MssUser() {
    }

    public MssUser(int userId, boolean active, LocalDate registrationDate, String email, String password, String userTypeId, String firstName, String lastName, LocalDate dateOfBirth, String placeOfBirth, String mothersName, String TAJNumber, String gender, String address, String phoneNumber) {
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
        this.TAJNumber = TAJNumber;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "MssUser{" +
                "userId=" + userId +
                ", active=" + active +
                ", registrationDate=" + registrationDate +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userTypeId='" + userTypeId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", mothersName='" + mothersName + '\'' +
                ", TAJNumber='" + TAJNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MssUser mssUser = (MssUser) o;
        return userId == mssUser.userId && active == mssUser.active && Objects.equals(registrationDate, mssUser.registrationDate) && Objects.equals(email, mssUser.email) && Objects.equals(password, mssUser.password) && Objects.equals(userTypeId, mssUser.userTypeId) && Objects.equals(firstName, mssUser.firstName) && Objects.equals(lastName, mssUser.lastName) && Objects.equals(dateOfBirth, mssUser.dateOfBirth) && Objects.equals(placeOfBirth, mssUser.placeOfBirth) && Objects.equals(mothersName, mssUser.mothersName) && Objects.equals(TAJNumber, mssUser.TAJNumber) && Objects.equals(gender, mssUser.gender) && Objects.equals(address, mssUser.address) && Objects.equals(phoneNumber, mssUser.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, active, registrationDate, email, password, userTypeId, firstName, lastName, dateOfBirth, placeOfBirth, mothersName, TAJNumber, gender, address, phoneNumber);
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

    public String getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(String userTypeId) {
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

    public String getTAJNumber() {
        return TAJNumber;
    }

    public void setTAJNumber(String TAJNumber) {
        this.TAJNumber = TAJNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}



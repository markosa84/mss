package hu.ak_akademia.mss.model.user;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class MssUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private boolean active;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime registrationDate = LocalDateTime.now(); // automatically LocalDate.now() value
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String roles;

    public MssUser() {
    }

    public MssUser(int userId, boolean active, LocalDateTime registrationDate, String email, String password, String firstName, String lastName, String gender, String phoneNumber, String roles) {
        this.userId = userId;
        this.active = active;
        this.registrationDate = registrationDate;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MssUser mssUser = (MssUser) o;
        return userId == mssUser.userId && active == mssUser.active && Objects.equals(registrationDate, mssUser.registrationDate) && Objects.equals(email, mssUser.email) && Objects.equals(password, mssUser.password) && Objects.equals(firstName, mssUser.firstName) && Objects.equals(lastName, mssUser.lastName) && Objects.equals(gender, mssUser.gender) && Objects.equals(phoneNumber, mssUser.phoneNumber) && Objects.equals(roles, mssUser.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, active, registrationDate, email, password, firstName, lastName, gender, phoneNumber, roles);
    }

    @Override
    public String toString() {
        return "MssUser{" +
                "userId=" + userId +
                ", active=" + active +
                ", registrationDate=" + registrationDate +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", roles='" + roles + '\'' +
                '}';
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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}



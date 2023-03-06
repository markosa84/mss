package hu.ak_akademia.mss.model.user;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

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

    public MssUser() {
    }

    public MssUser(int userId, boolean active, LocalDateTime registrationDate, String email, String password,  String firstName, String lastName, String gender, String phoneNumber) {
        this.userId = userId;
        this.active = active;
        this.registrationDate = registrationDate;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
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
 private String roles;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MssUser mssUser)) return false;
        return getUserId() == mssUser.getUserId() && isActive() == mssUser.isActive() && Objects.equals(getRegistrationDate(), mssUser.getRegistrationDate()) && Objects.equals(getEmail(), mssUser.getEmail()) && Objects.equals(getPassword(), mssUser.getPassword()) && Objects.equals(getFirstName(), mssUser.getFirstName()) && Objects.equals(getLastName(), mssUser.getLastName()) && Objects.equals(getGender(), mssUser.getGender()) && Objects.equals(getPhoneNumber(), mssUser.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), isActive(), getRegistrationDate(), getEmail(), getPassword(), getFirstName(), getLastName(), getGender(), getPhoneNumber());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MssUser.class.getSimpleName() + "[", "]")
                .add("userId=" + userId)
                .add("active=" + active)
                .add("registrationDate=" + registrationDate)
                .add("email='" + email + "'")
                .add("password='" + password + "'")
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("gender='" + gender + "'")
                .add("phoneNumber='" + phoneNumber + "'")
                .toString();
    }


    String getRoles() {
        return roles;
    }

   private  void setRoles(String roles) {
        this.roles = roles;
    }
}



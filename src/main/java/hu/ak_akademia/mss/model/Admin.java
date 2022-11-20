package hu.ak_akademia.mss.model;

import java.time.LocalDate;
import java.util.Objects;

public class Admin {

    private int adminId;
    private boolean active;
    private LocalDate registrationDate;
    private  String userName;
    private String email;
    private String  password;
    private String phoneNumber;

   public Admin(){
   }

    public Admin(int adminId, boolean active, LocalDate registrationDate, String userName, String email, String password, String phoneNumber) {
        this.adminId = adminId;
        this.active = active;
        this.registrationDate = registrationDate;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
   }

    public int getAdminId() {
        return adminId;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public boolean isActive() {
        return active;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;

        if (adminId != admin.adminId) return false;
        if (active != admin.active) return false;
        if (!Objects.equals(registrationDate, admin.registrationDate))
            return false;
        if (!Objects.equals(userName, admin.userName)) return false;
        if (!Objects.equals(email, admin.email)) return false;
        if (!Objects.equals(password, admin.password)) return false;
        return Objects.equals(phoneNumber, admin.phoneNumber);
    }

    @Override
    public int hashCode() {
        int result = adminId;
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", active=" + active +
                ", registrationDate=" + registrationDate +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

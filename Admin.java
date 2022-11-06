package hu.ak_akademia.mss;

public class Admin {

    private int adminId;
    private boolean  active;
    private int registrationDate;
    private  String userName;
    private String email;
    private String  password;
    private String phoneNumber;

   public Admin(){
   }

    public Admin(int adminId, boolean active, int registrationDate, String userName, String email, String password, String phoneNumber) {
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

    public int getRegistrationDate() {
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

    public void setRegistrationDate(int registrationDate) {
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
        if (!(o instanceof Admin admin)) return false;

        if (getAdminId() != admin.getAdminId()) return false;
        if (isActive() != admin.isActive()) return false;
        if (getRegistrationDate() != admin.getRegistrationDate()) return false;
        if (!getUserName().equals(admin.getUserName())) return false;
        if (!getEmail().equals(admin.getEmail())) return false;
        if (!getPassword().equals(admin.getPassword())) return false;
        return getPhoneNumber().equals(admin.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        int result = getAdminId();
        result = 31 * result + (isActive() ? 1 : 0);
        result = 31 * result + getRegistrationDate();
        result = 31 * result + getUserName().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getPhoneNumber().hashCode();
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

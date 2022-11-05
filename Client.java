import java.util.Objects;

public class Client {

    private int clientId;
    private boolean active;
    private int registrationDate;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int birthDay;
    private String birthPlace;
    private String mothersName;
    private int tajNumber;
    private String gender;
    private String nationality;
    private String motherLanguage;
    private String job;
    private String adress;
    private int reservations;
    private int medicalRecords;
    private int bills;
    private int financialBalance;
    private String phoneNumber;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(int registrationDate) {
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

    public int getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public int getTajNumber() {
        return tajNumber;
    }

    public void setTajNumber(int tajNumber) {
        this.tajNumber = tajNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMotherLanguage() {
        return motherLanguage;
    }

    public void setMotherLanguage(String motherLanguage) {
        this.motherLanguage = motherLanguage;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getReservations() {
        return reservations;
    }

    public void setReservations(int reservations) {
        this.reservations = reservations;
    }

    public int getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(int medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    public int getBills() {
        return bills;
    }

    public void setBills(int bills) {
        this.bills = bills;
    }

    public int getFinancialBalance() {
        return financialBalance;
    }

    public void setFinancialBalance(int financialBalance) {
        this.financialBalance = financialBalance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Client() {
    }

    public Client(int clientId, boolean active, int registrationDate, String email, String password, String firstName, String lastName, int birthDay, String birthPlace, String mothersName, int tajNumber, String gender, String nationality, String motherLanguage, String job, String adress, int reservations, int medicalRecords, int bills, int financialBalance, String phoneNumber) {
        this.clientId = clientId;
        this.active = active;
        this.registrationDate = registrationDate;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.birthPlace = birthPlace;
        this.mothersName = mothersName;
        this.tajNumber = tajNumber;
        this.gender = gender;
        this.nationality = nationality;
        this.motherLanguage = motherLanguage;
        this.job = job;
        this.adress = adress;
        this.reservations = reservations;
        this.medicalRecords = medicalRecords;
        this.bills = bills;
        this.financialBalance = financialBalance;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (clientId != client.clientId) return false;
        if (active != client.active) return false;
        if (registrationDate != client.registrationDate) return false;
        if (birthDay != client.birthDay) return false;
        if (tajNumber != client.tajNumber) return false;
        if (reservations != client.reservations) return false;
        if (medicalRecords != client.medicalRecords) return false;
        if (bills != client.bills) return false;
        if (financialBalance != client.financialBalance) return false;
        if (!Objects.equals(email, client.email)) return false;
        if (!Objects.equals(password, client.password)) return false;
        if (!Objects.equals(firstName, client.firstName)) return false;
        if (!Objects.equals(lastName, client.lastName)) return false;
        if (!Objects.equals(birthPlace, client.birthPlace)) return false;
        if (!Objects.equals(mothersName, client.mothersName)) return false;
        if (!Objects.equals(gender, client.gender)) return false;
        if (!Objects.equals(nationality, client.nationality)) return false;
        if (!Objects.equals(motherLanguage, client.motherLanguage))
            return false;
        if (!Objects.equals(job, client.job)) return false;
        if (!Objects.equals(adress, client.adress)) return false;
        return Objects.equals(phoneNumber, client.phoneNumber);
    }

    @Override
    public int hashCode() {
        int result = clientId;
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + registrationDate;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + birthDay;
        result = 31 * result + (birthPlace != null ? birthPlace.hashCode() : 0);
        result = 31 * result + (mothersName != null ? mothersName.hashCode() : 0);
        result = 31 * result + tajNumber;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + (motherLanguage != null ? motherLanguage.hashCode() : 0);
        result = 31 * result + (job != null ? job.hashCode() : 0);
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        result = 31 * result + reservations;
        result = 31 * result + medicalRecords;
        result = 31 * result + bills;
        result = 31 * result + financialBalance;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", active=" + active +
                ", registrationDate=" + registrationDate +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + birthDay +
                ", birthPlace='" + birthPlace + '\'' +
                ", mothersName='" + mothersName + '\'' +
                ", tajNumber=" + tajNumber +
                ", gender='" + gender + '\'' +
                ", nationality='" + nationality + '\'' +
                ", motherLanguage='" + motherLanguage + '\'' +
                ", job='" + job + '\'' +
                ", adress='" + adress + '\'' +
                ", reservations=" + reservations +
                ", medicalRecords=" + medicalRecords +
                ", bills=" + bills +
                ", financialBalance=" + financialBalance +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

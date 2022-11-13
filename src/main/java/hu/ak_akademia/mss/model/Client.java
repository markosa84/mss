package hu.ak_akademia.mss.model;

import java.time.LocalDate;
import java.util.Objects;

public class Client {

    private int clientId;
    private boolean active;
    private LocalDate registrationDate;
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
    private String address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Client(int clientId, boolean active, LocalDate registrationDate, String email, String password, String firstName, String lastName, int birthDay, String birthPlace, String mothersName, int tajNumber, String gender, String nationality, String motherLanguage, String job, String adress, int reservations, int medicalRecords, int bills, int financialBalance, String phoneNumber) {
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
        this.address = adress;
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
        return clientId == client.clientId && active == client.active && birthDay == client.birthDay && tajNumber == client.tajNumber && reservations == client.reservations && medicalRecords == client.medicalRecords && bills == client.bills && financialBalance == client.financialBalance && Objects.equals(registrationDate, client.registrationDate) && Objects.equals(email, client.email) && Objects.equals(password, client.password) && Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName) && Objects.equals(birthPlace, client.birthPlace) && Objects.equals(mothersName, client.mothersName) && Objects.equals(gender, client.gender) && Objects.equals(nationality, client.nationality) && Objects.equals(motherLanguage, client.motherLanguage) && Objects.equals(job, client.job) && Objects.equals(address, client.address) && Objects.equals(phoneNumber, client.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, active, registrationDate, email, password, firstName, lastName, birthDay, birthPlace, mothersName, tajNumber, gender, nationality, motherLanguage, job, address, reservations, medicalRecords, bills, financialBalance, phoneNumber);
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
                ", adress='" + address + '\'' +
                ", reservations=" + reservations +
                ", medicalRecords=" + medicalRecords +
                ", bills=" + bills +
                ", financialBalance=" + financialBalance +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

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
    private int birthday;
    private String birthPlace;
    private String motherName;
    private int TAJNumber;
    private String gender;
    private String nationality;
    private String motherLanguage;
    private String job;
    private String address;
    private int reservations;
    private int medicalRecords;
    private int bill;
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

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public int getTAJNumber() {
        return TAJNumber;
    }

    public void setTAJNumber(int TAJNumber) {
        this.TAJNumber = TAJNumber;
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

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
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

    public Client(int clientId, boolean active, LocalDate registrationDate, String email, String password, String firstName, String lastName, int birthDay, String birthPlace, String mothersName, int tajNumber, String gender, String nationality, String motherLanguage, String job, String address, int reservations, int medicalRecords, int bills, int financialBalance, String phoneNumber) {
        this.clientId = clientId;
        this.active = active;
        this.registrationDate = registrationDate;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthDay;
        this.birthPlace = birthPlace;
        this.motherName = mothersName;
        this.TAJNumber = tajNumber;
        this.gender = gender;
        this.nationality = nationality;
        this.motherLanguage = motherLanguage;
        this.job = job;
        this.address = address;
        this.reservations = reservations;
        this.medicalRecords = medicalRecords;
        this.bill = bills;
        this.financialBalance = financialBalance;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return clientId == client.clientId && active == client.active && birthday == client.birthday && TAJNumber == client.TAJNumber && reservations == client.reservations && medicalRecords == client.medicalRecords && bill == client.bill && financialBalance == client.financialBalance && Objects.equals(registrationDate, client.registrationDate) && Objects.equals(email, client.email) && Objects.equals(password, client.password) && Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName) && Objects.equals(birthPlace, client.birthPlace) && Objects.equals(motherName, client.motherName) && Objects.equals(gender, client.gender) && Objects.equals(nationality, client.nationality) && Objects.equals(motherLanguage, client.motherLanguage) && Objects.equals(job, client.job) && Objects.equals(address, client.address) && Objects.equals(phoneNumber, client.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, active, registrationDate, email, password, firstName, lastName, birthday, birthPlace, motherName, TAJNumber, gender, nationality, motherLanguage, job, address, reservations, medicalRecords, bill, financialBalance, phoneNumber);
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
                ", birthDay=" + birthday +
                ", birthPlace='" + birthPlace + '\'' +
                ", mothersName='" + motherName + '\'' +
                ", tajNumber=" + TAJNumber +
                ", gender='" + gender + '\'' +
                ", nationality='" + nationality + '\'' +
                ", motherLanguage='" + motherLanguage + '\'' +
                ", job='" + job + '\'' +
                ", address='" + address + '\'' +
                ", reservations=" + reservations +
                ", medicalRecords=" + medicalRecords +
                ", bills=" + bill +
                ", financialBalance=" + financialBalance +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

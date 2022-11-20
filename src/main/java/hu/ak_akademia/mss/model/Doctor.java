package hu.ak_akademia.mss.model;

import java.util.Objects;

public class Doctor {

    private int doctorId;
    private boolean active;
    private int registrationDate;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int birthDay;
    private String birthPlace;
    private String mothersName;
    private String gender;
    private String nationality;
    private String foreignLanguage;
    private String areaOfExpertise;
    private String address;
    private int reservations;
    private int appointments;
    private int financialBalance;
    private String phoneNumber;

    public Doctor() {
    }

    public Doctor(int doctorId, boolean active, int registrationDate, String email, String password, String firstName, String lastName, int birthDay, String birthPlace, String mothersName, String gender, String nationality, String foreignLanguage, String areaOfExpertise, String address, int reservations, int appointments, int financialBalance, String phoneNumber) {
        this.doctorId = doctorId;
        this.active = active;
        this.registrationDate = registrationDate;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.birthPlace = birthPlace;
        this.mothersName = mothersName;
        this.gender = gender;
        this.nationality = nationality;
        this.foreignLanguage = foreignLanguage;
        this.areaOfExpertise = areaOfExpertise;
        this.address = address;
        this.reservations = reservations;
        this.appointments = appointments;
        this.financialBalance = financialBalance;
        this.phoneNumber = phoneNumber;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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

    public String getForeignLanguage() {
        return foreignLanguage;
    }

    public void setForeignLanguage(String foreignLanguage) {
        this.foreignLanguage = foreignLanguage;
    }

    public String getAreaOfExpertise() {
        return areaOfExpertise;
    }

    public void setAreaOfExpertise(String areaOfExpertise) {
        this.areaOfExpertise = areaOfExpertise;
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

    public int getAppointments() {
        return appointments;
    }

    public void setAppointments(int appointments) {
        this.appointments = appointments;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doctor doctor = (Doctor) o;

        if (doctorId != doctor.doctorId) return false;
        if (active != doctor.active) return false;
        if (registrationDate != doctor.registrationDate) return false;
        if (birthDay != doctor.birthDay) return false;
        if (reservations != doctor.reservations) return false;
        if (appointments != doctor.appointments) return false;
        if (financialBalance != doctor.financialBalance) return false;
        if (!Objects.equals(email, doctor.email)) return false;
        if (!Objects.equals(password, doctor.password)) return false;
        if (!Objects.equals(firstName, doctor.firstName)) return false;
        if (!Objects.equals(lastName, doctor.lastName)) return false;
        if (!Objects.equals(birthPlace, doctor.birthPlace)) return false;
        if (!Objects.equals(mothersName, doctor.mothersName)) return false;
        if (!Objects.equals(gender, doctor.gender)) return false;
        if (!Objects.equals(nationality, doctor.nationality)) return false;
        if (!Objects.equals(foreignLanguage, doctor.foreignLanguage))
            return false;
        if (!Objects.equals(areaOfExpertise, doctor.areaOfExpertise))
            return false;
        if (!Objects.equals(address, doctor.address)) return false;
        return Objects.equals(phoneNumber, doctor.phoneNumber);
    }

    @Override
    public int hashCode() {
        int result = doctorId;
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + registrationDate;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + birthDay;
        result = 31 * result + (birthPlace != null ? birthPlace.hashCode() : 0);
        result = 31 * result + (mothersName != null ? mothersName.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + (foreignLanguage != null ? foreignLanguage.hashCode() : 0);
        result = 31 * result + (areaOfExpertise != null ? areaOfExpertise.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + reservations;
        result = 31 * result + appointments;
        result = 31 * result + financialBalance;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", active=" + active +
                ", registrationDate=" + registrationDate +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + birthDay +
                ", birthPlace='" + birthPlace + '\'' +
                ", mothersName='" + mothersName + '\'' +
                ", gender='" + gender + '\'' +
                ", nationality='" + nationality + '\'' +
                ", foreignLanguage='" + foreignLanguage + '\'' +
                ", areaOfExpertise='" + areaOfExpertise + '\'' +
                ", address='" + address + '\'' +
                ", reservations=" + reservations +
                ", appointments=" + appointments +
                ", financialBalance=" + financialBalance +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

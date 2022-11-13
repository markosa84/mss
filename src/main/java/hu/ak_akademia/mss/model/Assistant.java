package hu.ak_akademia.mss.model;

import java.time.LocalDate;

public class Assistant {
    private int assistantId;
    private boolean active;
    private LocalDate registrationDate;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;  // TODO: is it one word?
    private String brithPlace;
    private String mothersName;  // TODO: plural?
    private String gender;
    private String nationality;
    private String foreignLanguage;
    private int doctorsId;
    private int areaOfExpertise; //TODO: why the type is 'int'?
    private String address;
    private int reservations; // TODO: plural?
    private int appointments; // TODO: plural?
    private String phoneNumber;

    public Assistant() {
    }

    public Assistant(int assistantId, boolean active, LocalDate registrationDate, String email, String password, String firstName, String lastName, LocalDate birthDay, String brithPlace, String mothersName, String gender, String nationality, String foreignLanguage, int doctorsId, int areaOfExpertise, String address, int reservations, int appointments, String phoneNumber) {
        this.assistantId = assistantId;
        this.active = active;
        this.registrationDate = registrationDate;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.brithPlace = brithPlace;
        this.mothersName = mothersName;
        this.gender = gender;
        this.nationality = nationality;
        this.foreignLanguage = foreignLanguage;
        this.doctorsId = doctorsId;
        this.areaOfExpertise = areaOfExpertise;
        this.address = address;
        this.reservations = reservations;
        this.appointments = appointments;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAppointments() {
        return appointments;
    }

    public void setAppointments(int appointments) {
        this.appointments = appointments;
    }

    public int getReservations() {
        return reservations;
    }

    public void setReservations(int reservations) {
        this.reservations = reservations;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAreaOfExpertise() {
        return areaOfExpertise;
    }

    public void setAreaOfExpertise(int areaOfExpertise) {
        this.areaOfExpertise = areaOfExpertise;
    }

    public int getDoctorsId() {
        return doctorsId;
    }

    public void setDoctorsId(int doctorsId) {
        this.doctorsId = doctorsId;
    }

    public String getForeignLanguage() {
        return foreignLanguage;
    }

    public void setForeignLanguage(String foreignLanguage) {
        this.foreignLanguage = foreignLanguage;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public String getBrithPlace() {
        return brithPlace;
    }

    public void setBrithPlace(String brithPlace) {
        this.brithPlace = brithPlace;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(int assistantId) {
        this.assistantId = assistantId;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Assistant)) return false;
        if (!super.equals(object)) return false;

        Assistant assistant = (Assistant) object;

        if (getAssistantId() != assistant.getAssistantId()) return false;
        if (isActive() != assistant.isActive()) return false;
        if (getDoctorsId() != assistant.getDoctorsId()) return false;
        if (getAreaOfExpertise() != assistant.getAreaOfExpertise()) return false;
        if (getReservations() != assistant.getReservations()) return false;
        if (getAppointments() != assistant.getAppointments()) return false;
        if (!getRegistrationDate().equals(assistant.getRegistrationDate())) return false;
        if (!getEmail().equals(assistant.getEmail())) return false;
        if (!getPassword().equals(assistant.getPassword())) return false;
        if (!getFirstName().equals(assistant.getFirstName())) return false;
        if (!getLastName().equals(assistant.getLastName())) return false;
        if (!getBirthDay().equals(assistant.getBirthDay())) return false;
        if (!getBrithPlace().equals(assistant.getBrithPlace())) return false;
        if (!getMothersName().equals(assistant.getMothersName())) return false;
        if (!getGender().equals(assistant.getGender())) return false;
        if (!getNationality().equals(assistant.getNationality())) return false;
        if (!getForeignLanguage().equals(assistant.getForeignLanguage())) return false;
        if (!getAddress().equals(assistant.getAddress())) return false;
        if (!getPhoneNumber().equals(assistant.getPhoneNumber())) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getAssistantId();
        result = 31 * result + (isActive() ? 1 : 0);
        result = 31 * result + getRegistrationDate().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getBirthDay().hashCode();
        result = 31 * result + getBrithPlace().hashCode();
        result = 31 * result + getMothersName().hashCode();
        result = 31 * result + getGender().hashCode();
        result = 31 * result + getNationality().hashCode();
        result = 31 * result + getForeignLanguage().hashCode();
        result = 31 * result + getDoctorsId();
        result = 31 * result + getAreaOfExpertise();
        result = 31 * result + getAddress().hashCode();
        result = 31 * result + getReservations();
        result = 31 * result + getAppointments();
        result = 31 * result + getPhoneNumber().hashCode();
        return result;
    }

    @Override
    public java.lang.String toString() {
        return "Assistant{" +
                "assistantId=" + assistantId +
                ", active=" + active +
                ", registrationDate=" + registrationDate +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + birthDay +
                ", brithPlace='" + brithPlace + '\'' +
                ", mothersName='" + mothersName + '\'' +
                ", gender='" + gender + '\'' +
                ", nationality='" + nationality + '\'' +
                ", foreignLanguage='" + foreignLanguage + '\'' +
                ", doctorsId=" + doctorsId +
                ", areaOfExpertise='" + address + '\'' +
                ", reservations=" + reservations +
                ", appointments=" + appointments +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

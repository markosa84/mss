package hu.ak_akademia.mss.model;

import java.time.LocalDate;

public class FinancialColleague {
    private int finan_colleagueID;
    private boolean active;
    private LocalDate registractionDate;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String birthDay;
    private String birthPlace;
    private String mothersName;
    private String gender;
    private String nationality;
    private int centersIds;
    private int roomsIds;
    private String address;
    private String phoneNumber;

    public FinancialColleague() {

    }

    public FinancialColleague(int finan_colleagueID, boolean active, LocalDate registractionDate, String email, String password, String firstName, String lastName, String birthDay, String birthPlace, String mothersName, String gender, String nationality, int centersIds, int roomsIds, String address, String phoneNumber) {
        this.finan_colleagueID = finan_colleagueID;
        this.active = active;
        this.registractionDate = registractionDate;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.birthPlace = birthPlace;
        this.mothersName = mothersName;
        this.gender = gender;
        this.nationality = nationality;
        this.centersIds = centersIds;
        this.roomsIds = roomsIds;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getFinan_colleagueID() {
        return finan_colleagueID;
    }

    public void setFinan_colleagueID(int finan_colleagueID) {
        this.finan_colleagueID = finan_colleagueID;
    }

    public LocalDate getRegistractionDate() {
        return registractionDate;
    }

    public void setRegistractionDate(LocalDate registractionDate) {
        this.registractionDate = registractionDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
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

    public int getCentersIds() {
        return centersIds;
    }

    public void setCentersIds(int centersIds) {
        this.centersIds = centersIds;
    }

    public int getRoomsIds() {
        return roomsIds;
    }

    public void setRoomsIds(int roomsIds) {
        this.roomsIds = roomsIds;
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

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof FinancialColleague)) return false;
        if (!super.equals(object)) return false;

        FinancialColleague that = (FinancialColleague) object;

        if (getFinan_colleagueID() != that.getFinan_colleagueID()) return false;
        if (isActive() != that.isActive()) return false;
        if (getCentersIds() != that.getCentersIds()) return false;
        if (getRoomsIds() != that.getRoomsIds()) return false;
        if (!getRegistractionDate().equals(that.getRegistractionDate())) return false;
        if (!getEmail().equals(that.getEmail())) return false;
        if (!getPassword().equals(that.getPassword())) return false;
        if (!getFirstName().equals(that.getFirstName())) return false;
        if (!getLastName().equals(that.getLastName())) return false;
        if (!getBirthDay().equals(that.getBirthDay())) return false;
        if (!getBirthPlace().equals(that.getBirthPlace())) return false;
        if (!getMothersName().equals(that.getMothersName())) return false;
        if (!getGender().equals(that.getGender())) return false;
        if (!getNationality().equals(that.getNationality())) return false;
        if (!getAddress().equals(that.getAddress())) return false;
        if (!getPhoneNumber().equals(that.getPhoneNumber())) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getFinan_colleagueID();
        result = 31 * result + (isActive() ? 1 : 0);
        result = 31 * result + getRegistractionDate().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getBirthDay().hashCode();
        result = 31 * result + getBirthPlace().hashCode();
        result = 31 * result + getMothersName().hashCode();
        result = 31 * result + getGender().hashCode();
        result = 31 * result + getNationality().hashCode();
        result = 31 * result + getCentersIds();
        result = 31 * result + getRoomsIds();
        result = 31 * result + getAddress().hashCode();
        result = 31 * result + getPhoneNumber().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Financial_colleague{" +
                "finan_colleagueID=" + finan_colleagueID +
                ", active=" + active +
                ", registractionDate=" + registractionDate +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", birthPlace=" + birthPlace +
                ", mothersName='" + mothersName + '\'' +
                ", gender='" + gender + '\'' +
                ", nationality='" + nationality + '\'' +
                ", centersIds=" + centersIds +
                ", roomsIds=" + roomsIds +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

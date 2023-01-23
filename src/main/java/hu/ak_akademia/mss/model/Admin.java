package hu.ak_akademia.mss.model;


import java.time.LocalDate;

public class Admin {

    private MssUsers mssUsers;

    public MssUsers getMssUsers() {
        return mssUsers;
    }

    public int getUserId() {
        return mssUsers.getUserId();
    }

    public void setUserId(int userId) {
        mssUsers.setUserId(userId);
    }

    public boolean isActive() {
        return mssUsers.isActive();
    }

    public void setActive(boolean active) {
        mssUsers.setActive(active);
    }

    public LocalDate getRegistrationDate() {
        return mssUsers.getRegistrationDate();
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        mssUsers.setRegistrationDate(registrationDate);
    }

    public String getEmail() {
        return mssUsers.getEmail();
    }

    public void setEmail(String email) {
        mssUsers.setEmail(email);
    }

    public String getPassword() {
        return mssUsers.getPassword();
    }

    public void setPassword(String password) {
        mssUsers.setPassword(password);
    }

    public int getUserTypeId() {
        return mssUsers.getUserTypeId();
    }

    public void setUserTypeId(int userTypeId) {
        mssUsers.setUserTypeId(userTypeId);
    }

    public String getFirstName() {
        return mssUsers.getFirstName();
    }

    public void setFirstName(String firstName) {
        mssUsers.setFirstName(firstName);
    }

    public String getLastName() {
        return mssUsers.getLastName();
    }

    public void setLastName(String lastName) {
        mssUsers.setLastName(lastName);
    }

    public LocalDate getDateOfBirth() {
        return mssUsers.getDateOfBirth();
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        mssUsers.setDateOfBirth(dateOfBirth);
    }

    public String getPlaceOfBirth() {
        return mssUsers.getPlaceOfBirth();
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        mssUsers.setPlaceOfBirth(placeOfBirth);
    }

    public String getMothersName() {
        return mssUsers.getMothersName();
    }

    public void setMothersName(String mothersName) {
        mssUsers.setMothersName(mothersName);
    }

    public String getTajNumber() {
        return mssUsers.getTajNumber();
    }

    public void setTajNumber(String tajNumber) {
        mssUsers.setTajNumber(tajNumber);
    }

    public int getGenderId() {
        return mssUsers.getGenderId();
    }

    public void setGenderId(int genderId) {
        mssUsers.setGenderId(genderId);
    }

    public String getNationality() {
        return mssUsers.getNationality();
    }

    public void setNationality(String nationality) {
        mssUsers.setNationality(nationality);
    }

    public String getPreferableLanguage() {
        return mssUsers.getPreferableLanguage();
    }

    public void setPreferableLanguage(String preferableLanguage) {
        mssUsers.setPreferableLanguage(preferableLanguage);
    }

    public String getAddress() {
        return mssUsers.getAddress();
    }

    public void setAddress(String address) {
        mssUsers.setAddress(address);
    }

    public int getFinancialBalanceHuf() {
        throw new UnsupportedOperationException();
    }

    public void setFinancialBalanceHuf(int financialBalanceHuf) {
        mssUsers.setFinancialBalanceHuf(-1);
    }

    public String getAreaOfExpertise() {
        throw new UnsupportedOperationException();
    }

    public void setAreaOfExpertise(String areaOfExpertise) {
        mssUsers.setAreaOfExpertise(null);
    }

    public String getPhoneNumber() {
        return mssUsers.getPhoneNumber();
    }

    public void setPhoneNumber(String phoneNumber) {
        mssUsers.setPhoneNumber(phoneNumber);
    }


}

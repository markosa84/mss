public class Assistant {
    private int assitantId;
    private boolean active;
    private LocalDate registrationDate;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private String brithPlase;
    private String mothersName;
    private String gender;
    private String nationality;
    private String foreignLanguage;
    private int doctorsId;
    private int areaOfexpertise;
    private String adress;
    private int reservations;
    private int appointments;
    private String phoneNumber

    public Assistant() {

    }
      public Assistant(int assitantId, boolean active, LocalDate registrationDate, String email, String password, Stirng firstName, String lastName, LocalDate birthDay, String brithPlase, String mothersName,String gender, String nationality, String foreignLanguage, int doctorsId, int areaOfexpertise, String adress, int reservations, int appointments, String phoneNumber ){
        this.assitantId = assitantId;
        this.active = active;
        this.registrationDate = registrationDate;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.brithPlase = brithPlase;
        this.mothersName = mothersName;
        this.gender = gender;
        this.nationality = nationality;
        this.foreignLanguage = foreignLanguage;
        this.doctorsId = doctorsId;
        this.areaOfexpertise = areaOfexpertise;
        this.adress = adress;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getAreaOfexpertise() {
        return areaOfexpertise;
    }

    public void setAreaOfexpertise(int areaOfexpertise) {
        this.areaOfexpertise = areaOfexpertise;
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

    public String getBrithPlase() {
        return brithPlase;
    }

    public void setBrithPlase(String brithPlase) {
        this.brithPlase = brithPlase;
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

    public int getAssitantId() {
        return assitantId;
    }

    public void setAssitantId(int assitantId) {
        this.assitantId = assitantId;
    }
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Assistant)) return false;
        if (!super.equals(object)) return false;

        Assistant assistant = (Assistant) object;

        if (getAssitantId() != assistant.getAssitantId()) return false;
        if (isActive() != assistant.isActive()) return false;
        if (getDoctorsId() != assistant.getDoctorsId()) return false;
        if (getAreaOfexpertise() != assistant.getAreaOfexpertise()) return false;
        if (getReservations() != assistant.getReservations()) return false;
        if (getAppointments() != assistant.getAppointments()) return false;
        if (!getRegistrationDate().equals(assistant.getRegistrationDate())) return false;
        if (!getEmail().equals(assistant.getEmail())) return false;
        if (!getPassword().equals(assistant.getPassword())) return false;
        if (!getFirstName().equals(assistant.getFirstName())) return false;
        if (!getLastName().equals(assistant.getLastName())) return false;
        if (!getBirthDay().equals(assistant.getBirthDay())) return false;
        if (!getBrithPlase().equals(assistant.getBrithPlase())) return false;
        if (!getMothersName().equals(assistant.getMothersName())) return false;
        if (!getGender().equals(assistant.getGender())) return false;
        if (!getNationality().equals(assistant.getNationality())) return false;
        if (!getForeignLanguage().equals(assistant.getForeignLanguage())) return false;
        if (!getAdress().equals(assistant.getAdress())) return false;
        if (!getPhoneNumber().equals(assistant.getPhoneNumber())) return false;

        return true;
    }
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getAssitantId();
        result = 31 * result + (isActive() ? 1 : 0);
        result = 31 * result + getRegistrationDate().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getBirthDay().hashCode();
        result = 31 * result + getBrithPlase().hashCode();
        result = 31 * result + getMothersName().hashCode();
        result = 31 * result + getGender().hashCode();
        result = 31 * result + getNationality().hashCode();
        result = 31 * result + getForeignLanguage().hashCode();
        result = 31 * result + getDoctorsId();
        result = 31 * result + getAreaOfexpertise();
        result = 31 * result + getAdress().hashCode();
        result = 31 * result + getReservations();
        result = 31 * result + getAppointments();
        result = 31 * result + getPhoneNumber().hashCode();
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Assistant{" +
                "assitantId=" + assitantId +
                ", active=" + active +
                ", registrationDate=" + registrationDate +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + birthDay +
                ", brithPlase='" + brithPlase + '\'' +
                ", mothersName='" + mothersName + '\'' +
                ", gender='" + gender + '\'' +
                ", nationality='" + nationality + '\'' +
                ", foreignLanguage='" + foreignLanguage + '\'' +
                ", doctorsId=" + doctorsId +
                ", areaOfexpertise=" + areaOfexpertise +
                ", adress='" + adress + '\'' +
                ", reservations=" + reservations +
                ", appointments=" + appointments +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

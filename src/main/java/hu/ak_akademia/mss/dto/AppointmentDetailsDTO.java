package hu.ak_akademia.mss.dto;

public class AppointmentDetailsDTO {
    int appointmentID;
    int drID;
    String username;
    String startDate;
    String endDate;
    String areaOfExpertise;

    public AppointmentDetailsDTO(int appointmentID, int drID, String username, String startDate, String endDate, String areaOfExpertise) {
        this.appointmentID = appointmentID;
        this.drID = drID;
        this.username = username;
        this.startDate = startDate;
        this.endDate = endDate;
        this.areaOfExpertise = areaOfExpertise;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public int getDrID() {
        return drID;
    }

    public void setDrID(int drID) {
        this.drID = drID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAreaOfExpertise() {
        return areaOfExpertise;
    }

    public void setAreaOfExpertise(String areaOfExpertise) {
        this.areaOfExpertise = areaOfExpertise;
    }
}

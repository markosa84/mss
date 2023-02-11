package hu.ak_akademia.mss.model.user;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Doctor extends MssUser {

    private String areaOfExpertise;
    private int reservations;
    private int appointments;

    public Doctor() {
        super.setUserTypeId("Doctor");
    }

    public Doctor(String areaOfExpertise, int reservations, int appointments) {
        super.setUserTypeId("Doctor");
        this.areaOfExpertise = areaOfExpertise;
        this.reservations = reservations;
        this.appointments = appointments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Doctor doctor = (Doctor) o;
        return reservations == doctor.reservations && appointments == doctor.appointments && Objects.equals(areaOfExpertise, doctor.areaOfExpertise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), areaOfExpertise, reservations, appointments);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "areaOfExpertise='" + areaOfExpertise + '\'' +
                ", reservations=" + reservations +
                ", appointments=" + appointments +
                "} " + super.toString();
    }

    public String getAreaOfExpertise() {
        return areaOfExpertise;
    }

    public void setAreaOfExpertise(String areaOfExpertise) {
        this.areaOfExpertise = areaOfExpertise;
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


}

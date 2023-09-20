package hu.ak_akademia.mss.model;

import hu.ak_akademia.mss.model.user.MssUser;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private int id;


    @ManyToOne
    @JoinColumn(name = "client_id")
    private MssUser mssUserClient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private MssUser mssUserDoctor;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Appointmentstatus status;
    @ManyToOne
    @JoinColumn(name = "area_id")
    private AreaOfExpertise areaOfExpertise;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MssUser getMssUserClient() {
        return mssUserClient;
    }

    public void setMssUserClient(MssUser mssUserClient) {
        this.mssUserClient = mssUserClient;
    }

    public MssUser getMssUserDoctor() {
        return mssUserDoctor;
    }

    public void setMssUserDoctor(MssUser mssUserDoctor) {
        this.mssUserDoctor = mssUserDoctor;
    }

    public Appointmentstatus getStatus() {
        return status;
    }

    public void setStatus(Appointmentstatus status) {
        this.status = status;
    }

    public AreaOfExpertise getAreaOfExpertise() {
        return areaOfExpertise;
    }

    public void setAreaOfExpertise(AreaOfExpertise areaOfExpertise) {
        this.areaOfExpertise = areaOfExpertise;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return id == that.id && Objects.equals(mssUserClient, that.mssUserClient) && Objects.equals(mssUserDoctor, that.mssUserDoctor) && Objects.equals(status, that.status) && Objects.equals(areaOfExpertise, that.areaOfExpertise) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mssUserClient, mssUserDoctor, status, areaOfExpertise, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", mssUserClient=" + mssUserClient +
                ", mssUserDoctor=" + mssUserDoctor +
                ", status=" + status +
                ", areaOfExpertise=" + areaOfExpertise +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
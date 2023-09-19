package hu.ak_akademia.mss.model;


import hu.ak_akademia.mss.model.user.MssUser;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class DoctorsWorkingHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "working_hours_day_id")
    private int id;



    @ManyToOne
    @JoinColumn(name = "day_of_week_id")
    private DayOfWeek dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private AreaOfExpertise areaOfExpertise; // Szakterület

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private MssUser mssUserDoctor;
    private LocalTime startTime; // Munka kezdete
    private LocalTime endTime; // Munka vége

    public DoctorsWorkingHours() {
    }

    public DoctorsWorkingHours(int id, DayOfWeek dayOfWeek, AreaOfExpertise areaOfExpertise, MssUser mssUserDoctor, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.areaOfExpertise = areaOfExpertise;
        this.mssUserDoctor = mssUserDoctor;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public AreaOfExpertise getAreaOfExpertise() {
        return areaOfExpertise;
    }

    public void setAreaOfExpertise(AreaOfExpertise areaOfExpertise) {
        this.areaOfExpertise = areaOfExpertise;
    }

    public MssUser getMssUserDoctor() {
        return mssUserDoctor;
    }

    public void setMssUserDoctor(MssUser mssUserDoctor) {
        this.mssUserDoctor = mssUserDoctor;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorsWorkingHours that = (DoctorsWorkingHours) o;
        return id == that.id && Objects.equals(dayOfWeek, that.dayOfWeek) && Objects.equals(areaOfExpertise, that.areaOfExpertise) && Objects.equals(mssUserDoctor, that.mssUserDoctor) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dayOfWeek, areaOfExpertise, mssUserDoctor, startTime, endTime);
    }

    @Override
    public String toString() {
        return "DoctorsWorkingHours{" +
                "id=" + id +
                ", dayOfWeek=" + dayOfWeek +
                ", areaOfExpertise=" + areaOfExpertise +
                ", mssUserDoctor=" + mssUserDoctor +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}

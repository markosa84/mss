package hu.ak_akademia.mss.dto;

import hu.ak_akademia.mss.model.AreaOfExpertise;
import hu.ak_akademia.mss.model.DayOfWeek;
import hu.ak_akademia.mss.model.user.MssUser;

import java.time.LocalTime;
import java.util.StringJoiner;

public class DoctorsWorkingHoursDTO {

    private int id;
    private DayOfWeek dayOfWeek;
    private AreaOfExpertise areaOfExpertise;
    private MssUser mssUserDoctor;
    private LocalTime startTime;
    private LocalTime endTime;

    public DoctorsWorkingHoursDTO(LocalTime startTime, LocalTime endTime) {
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
    public String toString() {
        return new StringJoiner(", ", DoctorsWorkingHoursDTO.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("dayOfWeek=" + dayOfWeek)
                .add("areaOfExpertise=" + areaOfExpertise)
                .add("mssUserDoctor=" + mssUserDoctor)
                .add("startTime=" + startTime)
                .add("endTime=" + endTime)
                .toString();
    }
}

package hu.ak_akademia.mss.dto;

import java.util.List;

public class DoctorTimeSlotDto {
    private int doctorId;
    private List<Integer> unavailableSlotIds;

    public DoctorTimeSlotDto(int doctorId, List<Integer> unavailableSlotIds) {
        this.doctorId = doctorId;
        this.unavailableSlotIds = unavailableSlotIds;
    }
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public List<Integer> getUnavailableSlotIds() {
        return unavailableSlotIds;
    }

    public void setUnavailableSlotIds(List<Integer> unavailableSlotIds) {
        this.unavailableSlotIds = unavailableSlotIds;
    }
}


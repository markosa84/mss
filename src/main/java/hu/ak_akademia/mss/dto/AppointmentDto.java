package hu.ak_akademia.mss.dto;


import java.time.LocalDate;
import java.util.List;

public class AppointmentDto {
    private LocalDate date;
    private List<DoctorTimeSlotDto> doctorsTimeSlots;



    public AppointmentDto(LocalDate date, List<DoctorTimeSlotDto> doctorsTimeSlots) {
        this.date = date;
        this.doctorsTimeSlots = doctorsTimeSlots;
    }



    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<DoctorTimeSlotDto> getDoctorsTimeSlots() {
        return doctorsTimeSlots;
    }

    public void setDoctorsTimeSlots(List<DoctorTimeSlotDto> doctorsTimeSlots) {
        this.doctorsTimeSlots = doctorsTimeSlots;
    }
}

package hu.ak_akademia.mss.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record RequestAppointmentDto(Integer drId, LocalTime startTime, LocalTime endTime, LocalDate date,
                                    String username,
                                    String areaOfExpertise, int slotId) {
}

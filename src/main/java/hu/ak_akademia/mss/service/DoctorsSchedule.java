package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.dto.DoctorsWorkingHoursDTO;
import hu.ak_akademia.mss.model.AreaOfExpertise;
import hu.ak_akademia.mss.model.Slot;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DoctorsSchedule {

    private final AreaOfExpertise areaOfExpertise;

    public DoctorsSchedule(AreaOfExpertise areaOfExpertise) {
        this.areaOfExpertise = areaOfExpertise;
    }

    public List<Slot> generateSlots(DoctorsWorkingHoursService doctorsWorkingHours) {
        List<Slot> slots = new ArrayList<>();
        List<LocalTime> startAndEndWorkingTime = findStartAndEndWorkingTime(doctorsWorkingHours);
        LocalTime start = startAndEndWorkingTime.get(0);
        for (int i = 1; start.isBefore(startAndEndWorkingTime.get(1)); i++) {
            Slot slot = new Slot();
            slot.setSlotId(i);
            slot.setStartTime(start);
            slot.setEndTime(start = start.plusMinutes(doctorsWorkingHours.getSlotInterval()));
            slots.add(slot);
        }
        return slots;
    }

    private List<LocalTime> findStartAndEndWorkingTime(DoctorsWorkingHoursService doctorsWorkingHours) {
        List<DoctorsWorkingHoursDTO> list = doctorsWorkingHours.getAreaOfExpertiseSchedule(areaOfExpertise.getAreaOfExpertiseId());
        var startTime = list.stream()
                .map(DoctorsWorkingHoursDTO::getStartTime)
                .min(LocalTime::compareTo)
                .orElseThrow();
        var endTime = list.stream()
                .map(DoctorsWorkingHoursDTO::getEndTime)
                .max(LocalTime::compareTo)
                .orElseThrow();
        return List.of(startTime, endTime);
    }

}

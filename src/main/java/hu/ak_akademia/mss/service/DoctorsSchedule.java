package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.dto.DoctorsWorkingHoursDTO;
import hu.ak_akademia.mss.model.AreaOfExpertise;
import hu.ak_akademia.mss.model.ScheduleStartAndEndTime;
import hu.ak_akademia.mss.model.Slot;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DoctorsSchedule {

    private DoctorsWorkingHoursService workingHoursService;

    private AreaOfExpertise areaOfExpertise;

    public List<Slot> generateSlots() {
        var list = workingHoursService.getAreaOfExpertiseSchedule(areaOfExpertise.getAreaOfExpertiseId());
        List<Slot> slots = new ArrayList<>();
        var scheduleStartAndEndTime = findStartAndEndWorkingTime(list);
        LocalTime start = scheduleStartAndEndTime.startTime();
        for (int i = 1; start.isBefore(scheduleStartAndEndTime.endTime()); i++) {
            Slot slot = new Slot(i, start, start = start.plusMinutes(workingHoursService.getSlotInterval()));
            slots.add(slot);
        }
        return slots;
    }

    private ScheduleStartAndEndTime findStartAndEndWorkingTime(List<DoctorsWorkingHoursDTO> list) {
        var startTime = list.stream()
                .map(DoctorsWorkingHoursDTO::getStartTime)
                .min(LocalTime::compareTo)
                .orElseThrow();
        var endTime = list.stream()
                .map(DoctorsWorkingHoursDTO::getEndTime)
                .max(LocalTime::compareTo)
                .orElseThrow();
        return new ScheduleStartAndEndTime(startTime, endTime);
    }

    public void setAreaOfExpertise(AreaOfExpertise areaOfExpertise) {
        this.areaOfExpertise = areaOfExpertise;
    }

    public void setWorkingHoursService(DoctorsWorkingHoursService workingHoursService) {
        this.workingHoursService = workingHoursService;
    }
}

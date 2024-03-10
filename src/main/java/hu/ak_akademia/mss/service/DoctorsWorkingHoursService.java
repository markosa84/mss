package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.dto.DoctorsWorkingHoursDTO;
import hu.ak_akademia.mss.repository.DoctorsWorkingHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorsWorkingHoursService {

    @Autowired
    private Environment env;

    @Autowired
    private DoctorsWorkingHoursRepository doctorsWorkingHoursRepository;

    public Integer getSlotInterval() {
        return env.getProperty("slot.time", Integer.class);
    }

    public List<DoctorsWorkingHoursDTO> getAreaOfExpertiseSchedule(int areaId) {
        return doctorsWorkingHoursRepository.getAllDoctorsWorkingHours(areaId)
                .stream()
                .map(d -> new DoctorsWorkingHoursDTO(d.getStartTime(), d.getEndTime()))
                .toList();
    }

}

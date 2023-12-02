package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.dto.DoctorsWorkingHoursDTO;
import hu.ak_akademia.mss.repository.DoctorsWorkingHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<DoctorsWorkingHoursDTO> list = new ArrayList<>();
        for (var dwo : doctorsWorkingHoursRepository.getAllDoctorsWorkingHours(areaId)) {
            DoctorsWorkingHoursDTO dto = new DoctorsWorkingHoursDTO();
            dto.setStartTime(dwo.getStartTime());
            dto.setEndTime(dwo.getEndTime());
            list.add(dto);
        }
        return list;
    }
}

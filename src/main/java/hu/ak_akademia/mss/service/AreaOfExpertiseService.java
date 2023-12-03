package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.dto.AreaOfExpertiseDTO;
import hu.ak_akademia.mss.model.AreaOfExpertise;
import hu.ak_akademia.mss.model.Slot;
import hu.ak_akademia.mss.repository.AreaOfExpertiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaOfExpertiseService {

    @Autowired
    private AreaOfExpertiseRepository areaOfExpertiseRepository;

    @Autowired
    private DoctorsWorkingHoursService doctorsWorkingHoursService;

    public AreaOfExpertiseService() {
    }

    public List<AreaOfExpertise> getAllAreaOfExpertise() {
        return areaOfExpertiseRepository.findAll();
    }

    public String getAreaOfExpertiseById(int areaId) {
        Optional<AreaOfExpertise> areaOfExpertiseOptional = areaOfExpertiseRepository.findById(areaId);
        return areaOfExpertiseOptional.map(AreaOfExpertise::getName).orElse("Unknown area of expertise");
    }

    public List<AreaOfExpertiseDTO> getAreaOfExpertiseDTO() {
        var areaOfExpertiseDTO = new AreaOfExpertiseDTO();
        return areaOfExpertiseDTO.getAreOfExpertiseDTO();

    }

    public AreaOfExpertise getAreaById(int areaId) {
        return areaOfExpertiseRepository.getReferenceById(areaId);
    }

    public List<Slot> getSlots(int areaOfExpertiseId) {
        var areaOfExpertise = getAreaById(areaOfExpertiseId);
        DoctorsSchedule doctorsSchedule = new DoctorsSchedule();
        doctorsSchedule.setAreaOfExpertise(areaOfExpertise);
        doctorsSchedule.setWorkingHoursService(doctorsWorkingHoursService);
        return doctorsSchedule.generateSlots();
    }
}



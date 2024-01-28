package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.dto.AreaOfExpertiseDTO;
import hu.ak_akademia.mss.model.AreaOfExpertise;
import hu.ak_akademia.mss.model.Slot;
import hu.ak_akademia.mss.repository.AreaOfExpertiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Optional<AreaOfExpertise> getAreaById(int areaId) {
        return Optional.of(areaOfExpertiseRepository.getReferenceById(areaId));
    }

    public ResponseEntity<List<Slot>> getSlots(int areaOfExpertiseId) {
        var areaOfExpertise = getAreaById(areaOfExpertiseId);
        DoctorsSchedule doctorsSchedule = new DoctorsSchedule();
        doctorsSchedule.setAreaOfExpertise(areaOfExpertise.orElseThrow());
        doctorsSchedule.setWorkingHoursService(doctorsWorkingHoursService);
        return ResponseEntity.ok(doctorsSchedule.generateSlots());
    }

    public List<AreaOfExpertiseDTO> getAreOfExpertiseDTO() {
        ArrayList<AreaOfExpertiseDTO> listOfAreaOfExpertise = new ArrayList<>();
        for (AreaOfExpertise areaOfExpertise : areaOfExpertiseRepository.findAll()) {
            var DTO = new AreaOfExpertiseDTO();
            DTO.setName(areaOfExpertise.getName());
            DTO.setAreaOfExpertiseId(areaOfExpertise.getAreaOfExpertiseId());
            DTO.setDescription(areaOfExpertise.getDescription());
            listOfAreaOfExpertise.add(DTO);
        }
        return listOfAreaOfExpertise;
    }
}



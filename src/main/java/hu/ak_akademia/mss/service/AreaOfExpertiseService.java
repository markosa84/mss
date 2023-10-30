package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.AreaOfExpertise;
import hu.ak_akademia.mss.repository.AreaOfExpertiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AreaOfExpertiseService {
    @Autowired
    private AreaOfExpertiseRepository areaOfExpertiseRepository;

    public AreaOfExpertiseService() {


    }

    public List<AreaOfExpertise> getAllAreaOfExpertise() {
        return areaOfExpertiseRepository.findAll();
    }

    public String getAreaOfExpertiseById(int areaId) {
        Optional<AreaOfExpertise> areaOfExpertiseOptional = areaOfExpertiseRepository.findById(areaId);
        return areaOfExpertiseOptional.map(AreaOfExpertise::getQualification).orElse("Unknown area of expertise");
    }
}



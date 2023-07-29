package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.AreaOfExpertise;
import hu.ak_akademia.mss.model.user.Doctor;
import hu.ak_akademia.mss.model.user.MssUser;
import hu.ak_akademia.mss.repository.AreaOfExpertiseRepository;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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



package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.dto.DoctorForAreaOfExpertiseDto;
import hu.ak_akademia.mss.model.user.MssUser;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    // lehet ide jön még más is
    @Autowired
    private MSSUserRepository mssUserRepository;

    private List<MssUser> findDoctorsByAreaOfExpertise(int areaId) {
        return mssUserRepository.findDoctorsByAreaOfExpertise(areaId);
    }

    //Előállitsa a doktorDto-t
    public List<DoctorForAreaOfExpertiseDto> getDoctorDtosByAreaOfExpertise(int areaId) {
        List<MssUser> doctors = findDoctorsByAreaOfExpertise(areaId);
        List<DoctorForAreaOfExpertiseDto> doctorDtos = new ArrayList<>();
        for (MssUser doctor : doctors) {
            doctorDtos.add(new DoctorForAreaOfExpertiseDto(doctor));
        }

// röviditett változata
        //    List<DoctorForAreaOfExpertiseDto> doctorDtos = doctors.stream()
        //          .map(DoctorForAreaOfExpertiseDto::new)
        //        .collect(Collectors.toList());
        return doctorDtos;
    }
}
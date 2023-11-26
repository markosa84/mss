package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.dto.DoctorForAreaOfExpertiseDto;
import hu.ak_akademia.mss.model.user.MssUser;
import hu.ak_akademia.mss.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorForAreaOfExpertiseController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/area-of-expertise/{areaId}")
    public ResponseEntity<List<DoctorForAreaOfExpertiseDto>> getDoctorsByAreaOfExpertise(@PathVariable int areaId) {
        List<MssUser> doctors = doctorService.findDoctorsByAreaOfExpertise(areaId);

        List<DoctorForAreaOfExpertiseDto> doctorDtos = new ArrayList<>();
        for (MssUser doctor : doctors) {
            doctorDtos.add(new DoctorForAreaOfExpertiseDto(doctor));
        }

// proba
    //    List<DoctorForAreaOfExpertiseDto> doctorDtos = doctors.stream()
      //          .map(DoctorForAreaOfExpertiseDto::new)
        //        .collect(Collectors.toList());

        return ResponseEntity.ok(doctorDtos);
    }


}

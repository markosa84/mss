package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.dto.DoctorForAreaOfExpertiseDto;
import hu.ak_akademia.mss.model.user.MssUser;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private MSSUserRepository mssUserRepository;

    private List<MssUser> findDoctorsByAreaOfExpertise(int areaId) {
        return mssUserRepository.findDoctorsByAreaOfExpertise(areaId);
    }

    public List<DoctorForAreaOfExpertiseDto> getDoctorDtosByAreaOfExpertise(int areaId) {
        return findDoctorsByAreaOfExpertise(areaId).stream().map(DoctorForAreaOfExpertiseDto::new).toList();
    }

    public List<Integer> getDoctorIdsByAreaOfExpertise(int areaId) {
        return findDoctorsByAreaOfExpertise(areaId).stream().map(MssUser::getUserId).toList();
    }

    public  String getDoctorName ( int userId){
        var mssUser = mssUserRepository.findById(userId).orElseThrow();
        return mssUser.getLastName() + " " + mssUser.getFirstName();
    }
}
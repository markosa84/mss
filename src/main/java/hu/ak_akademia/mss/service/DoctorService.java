package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.user.MssUser;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
// lehet ide jön még más is
    @Autowired
    private MSSUserRepository mssUserRepository;

    public List<MssUser> findDoctorsByAreaOfExpertise(int areaId) {
        return mssUserRepository.findDoctorsByAreaOfExpertise(areaId);
    }
}

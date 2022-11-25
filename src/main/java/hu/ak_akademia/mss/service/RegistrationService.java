package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.MssUsers;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    private MSSUserRepository mssUserRepository;

    @Autowired
    public void setMssUserRepository(MSSUserRepository mssUserRepository) {
        this.mssUserRepository = mssUserRepository;
    }

    public List<MssUsers> getAllMSSUsers(String name, String password) {
        mssUserRepository.findAll();
        return null; //TODO make up code!
    }
}

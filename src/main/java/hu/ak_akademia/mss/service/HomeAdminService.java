package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.user.Client;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class HomeAdminService {

    MSSUserRepository mssUserRepository;

    @Autowired
    HomeAdminService(MSSUserRepository mssUserRepository){
        this.mssUserRepository = mssUserRepository;
    }

    public List<Client> getAllClients(){
        if (mssUserRepository.getAllClients().isPresent()) {
            return mssUserRepository.getAllClients().get();
        }
        return Collections.emptyList();
    }
}

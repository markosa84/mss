package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.user.Client;
import hu.ak_akademia.mss.model.user.Doctor;
import hu.ak_akademia.mss.model.user.MssUser;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeAdminService {

    MSSUserRepository mssUserRepository;

    @Autowired
    HomeAdminService(MSSUserRepository mssUserRepository) {
        this.mssUserRepository = mssUserRepository;
    }

    public List<Client> getAllClients() {
        return mssUserRepository.getAllClients().orElse(new ArrayList<>());
    }

    public List<Client> getAllActiveClients() {
        return mssUserRepository.getActiveClients().orElse(new ArrayList<>());
    }

    public List<Client> getAllInactiveClients() {
        return mssUserRepository.getInActiveClients().orElse(new ArrayList<>());
    }

    public List<Doctor> getAllDoctors() {
        return mssUserRepository.getDoctors().orElse(new ArrayList<>());
    }

    public List<Client> getClientByName(String firstName, String lastName) {
        return mssUserRepository.getClientByName(firstName, lastName).orElse(new ArrayList<>());
    }

    public List<Client> getClientByLastName(String lastName) {
        return mssUserRepository.getClientByLastName(lastName).orElse(new ArrayList<>());
    }

    public MssUser getById(int id) {
        return mssUserRepository.getMSSUserByUserId(id);
    }

    public void deleteById(int id) {
        mssUserRepository.deleteById(id);
    }

    public List<MssUser> getAll() {
        return mssUserRepository.findAll();
    }
}

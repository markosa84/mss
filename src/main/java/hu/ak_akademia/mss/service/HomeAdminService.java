package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.user.Client;
import hu.ak_akademia.mss.model.user.Doctor;
import hu.ak_akademia.mss.model.user.MssUser;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class HomeAdminService {

    MSSUserRepository mssUserRepository;

    @Autowired
    HomeAdminService(MSSUserRepository mssUserRepository){
        this.mssUserRepository = mssUserRepository;
    }

    public List<Client> getAllClients(){

        Optional<List<Client>> clients = mssUserRepository.getAllClients();

        if (clients.isPresent()) {
            return clients.get();
        }
        return Collections.emptyList();
    }

    public List<Client> getActiveClients(){

        Optional<List<Client>> activeClients = mssUserRepository.getActiveClients();

        if (activeClients.isPresent()) {
            return activeClients.get();
        }
        return Collections.emptyList();
    }

    public List<Client> getInActiveClients(){

        Optional<List<Client>> inActiveClients = mssUserRepository.getInActiveClients();

        if (inActiveClients.isPresent()) {
            return inActiveClients.get();
        }
        return Collections.emptyList();
    }

    public List<Doctor> getDoctors(){

        Optional<List<Doctor>> doctors = mssUserRepository.getDoctors();

        if (doctors.isPresent()) {
            return doctors.get();
        }
        return Collections.emptyList();
    }

    public Object getClientByName(String firstName, String lastName) {
        Optional<List<Client>> clientByName = mssUserRepository.getClientByName(firstName, lastName);
        if (clientByName.isPresent()){
            return clientByName.get();
        }
        return Collections.emptyList();
    }

    public Object getClientByLastName(String lastName) {
        Optional<List<Client>> clientByLastName = mssUserRepository.getClientByLastName(lastName);
        if (clientByLastName.isPresent()){
            return clientByLastName.get();
        }
        return Collections.emptyList();
    }

    public MssUser getById(int id) {
        return  mssUserRepository.getMSSUserByUserId(id);
    }

    public void deleteById(int id) {
        mssUserRepository.deleteById(id);
    }
}

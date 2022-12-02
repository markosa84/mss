package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.Client;
import hu.ak_akademia.mss.repository.ClientRepository;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    private ClientRepository clientRepository;

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAllClient() {
        return clientRepository.findAll();
    }

    public void saveClient(Client client) {
        var validator = new Validator();
        try {
            validator.process(client);
            clientRepository.save(client);
        } catch (IncorrectEnteredDataException e) {
            throw new RuntimeException(e);
        }
    }
}

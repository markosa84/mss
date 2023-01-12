package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.Client;
import hu.ak_akademia.mss.repository.ClientRepository;
import hu.ak_akademia.mss.service.validators.ClientValidatorFactory;
import hu.ak_akademia.mss.service.validators.CompositeClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public void save(Client client) {
        clientRepository.save(client);
    }

    public Map<String, String> testClientData(Client client) {
        var clientValidator = new CompositeClientValidator();
        clientValidator.addValidators(ClientValidatorFactory.getInstance().getAllClientValidators());
        clientValidator.validate(client);
        return clientValidator.getValidatorErrorList();
    }

    public void encryptPassword(Client client) {
        var passwordEncrypt = new PasswordEncryption();
        client.setPassword(passwordEncrypt.encrypt(client));
    }
}

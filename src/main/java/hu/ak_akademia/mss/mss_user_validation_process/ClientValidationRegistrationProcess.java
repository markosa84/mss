package hu.ak_akademia.mss.mss_user_validation_process;

import hu.ak_akademia.mss.dto.MssUserDto;
import hu.ak_akademia.mss.model.user.Client;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import hu.ak_akademia.mss.service.RegistrationService;
import hu.ak_akademia.mss.service.RegistrationVerificationService;
import hu.ak_akademia.mss.service.validators.CompositeClientValidator;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

public class ClientValidationRegistrationProcess implements MssUserValidationRegistrationProcess<Client> {

    private final RegistrationService registrationService;

    private final MSSUserRepository mssUserRepository;

    private final RegistrationVerificationService registrationVerificationService;

    public ClientValidationRegistrationProcess(RegistrationService registrationService, MSSUserRepository mssUserRepository, RegistrationVerificationService registrationVerificationService) {
        this.mssUserRepository = mssUserRepository;
        this.registrationService = registrationService;
        this.registrationVerificationService = registrationVerificationService;
    }

    @Override
    public ResponseEntity<Collection<String>> controlMssUserRegistrationProcess(MssUserDto mssUserDto) throws MessagingException {
        var client = mappingDtoToMssUser(mssUserDto);
        var clientValidator = new CompositeClientValidator(mssUserRepository);
        clientValidator.validate(client);
        if (clientValidator.getValidatorErrorList().isEmpty()) {
            registrationService.encryptPassword(client);
            registrationService.save(client);
            registrationVerificationService.performRegistrationVerification(client);
            return ResponseEntity.ok().body(Collections.emptyList());
        }
        return ResponseEntity.status(403).body(clientValidator.getValidatorErrorList().values());
    }

    @Override
    public Client mappingDtoToMssUser(MssUserDto mssUserDto) {
        var client = new Client();
        client.setEmail(mssUserDto.getEmail());
        client.setPassword(mssUserDto.getPassword());
        client.setActive(false);
        client.setRegistrationDate(LocalDateTime.now());
        client.setDateOfBirth(mssUserDto.getDateOfBirth());
        client.setMothersName(mssUserDto.getMothersName());
        client.setPlaceOfBirth(mssUserDto.getPlaceOfBirth());
        client.setTAJNumber(mssUserDto.getTajNumber());
        client.setFirstName(mssUserDto.getFirstName());
        client.setLastName(mssUserDto.getLastName());
        client.setLanguages(registrationService.getLanguages(mssUserDto.getLanguageId()));
        client.setGender(mssUserDto.getGenderId());
        client.setRoles("ROLE_CLIENT");
        client.setPhoneNumber(mssUserDto.getPhoneNumber());
        return client;
    }
}

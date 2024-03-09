package hu.ak_akademia.mss.mss_user_validation_process;

import hu.ak_akademia.mss.dto.MssUserDto;
import hu.ak_akademia.mss.model.user.Assistant;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import hu.ak_akademia.mss.service.RegistrationService;
import hu.ak_akademia.mss.service.RegistrationVerificationService;
import hu.ak_akademia.mss.service.validators.CompositeAssistantValidator;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

public class AssistantValidationRegistrationProcess implements MssUserValidationRegistrationProcess<Assistant> {

    private final RegistrationService registrationService;

    private final MSSUserRepository mssUserRepository;

    private final RegistrationVerificationService registrationVerificationService;

    public AssistantValidationRegistrationProcess(RegistrationService registrationService, MSSUserRepository mssUserRepository, RegistrationVerificationService registrationVerificationService) {
        this.mssUserRepository = mssUserRepository;
        this.registrationService = registrationService;
        this.registrationVerificationService = registrationVerificationService;
    }

    @Override
    public ResponseEntity<Collection<String>> controlMssUserRegistrationProcess(MssUserDto mssUserDto) throws MessagingException {
        var assistant = mappingDtoToMssUser(mssUserDto);
        var assistantValidator = new CompositeAssistantValidator(mssUserRepository);
        assistantValidator.validate(assistant);
        if (assistantValidator.getValidatorErrorList().isEmpty()) {
            registrationService.encryptPassword(assistant);
            registrationService.save(assistant);
            registrationVerificationService.performRegistrationVerification(assistant);
            return ResponseEntity.ok().body(Collections.emptyList());
        }
        return ResponseEntity.status(403).body(assistantValidator.getValidatorErrorList().values());
    }

    @Override
    public Assistant mappingDtoToMssUser(MssUserDto mssUserDto) {
        var assistant = new Assistant();
        assistant.setEmail(mssUserDto.getEmail());
        assistant.setFirstName(mssUserDto.getFirstName());
        assistant.setLastName(mssUserDto.getLastName());
        assistant.setPassword(mssUserDto.getPassword());
        assistant.setPhoneNumber(mssUserDto.getPhoneNumber());
        assistant.setActive(false);
        assistant.setRegistrationDate(LocalDateTime.now());
        assistant.setRoles("ROLE_ASSISTANT");
        return assistant;
    }
}

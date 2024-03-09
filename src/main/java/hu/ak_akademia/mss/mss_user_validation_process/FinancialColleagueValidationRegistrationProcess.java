package hu.ak_akademia.mss.mss_user_validation_process;

import hu.ak_akademia.mss.dto.MssUserDto;
import hu.ak_akademia.mss.model.user.FinancialColleague;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import hu.ak_akademia.mss.service.RegistrationService;
import hu.ak_akademia.mss.service.RegistrationVerificationService;
import hu.ak_akademia.mss.service.validators.CompositeColleagueValidator;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

public class FinancialColleagueValidationRegistrationProcess implements MssUserValidationRegistrationProcess<FinancialColleague> {

    private final MSSUserRepository mssUserRepository;
    private final RegistrationService registrationService;
    private final RegistrationVerificationService registrationVerificationService;

    public FinancialColleagueValidationRegistrationProcess(RegistrationService registrationService, MSSUserRepository mssUserRepository, RegistrationVerificationService registrationVerificationService) {
        this.mssUserRepository = mssUserRepository;
        this.registrationService = registrationService;
        this.registrationVerificationService = registrationVerificationService;
    }

    @Override
    public ResponseEntity<Collection<String>> controlMssUserRegistrationProcess(MssUserDto mssUserDto) throws MessagingException {
        var financialColleague = mappingDtoToMssUser(mssUserDto);
        var financialColleagueValidator = new CompositeColleagueValidator(mssUserRepository);
        financialColleagueValidator.validate(financialColleague);
        if (financialColleagueValidator.getValidatorErrorList().isEmpty()) {
            registrationService.encryptPassword(financialColleague);
            registrationService.save(financialColleague);
            registrationVerificationService.performRegistrationVerification(financialColleague);
            return ResponseEntity.ok().body(Collections.emptyList());
        }
        return ResponseEntity.status(403).body(financialColleagueValidator.getValidatorErrorList().values());
    }

    @Override
    public FinancialColleague mappingDtoToMssUser(MssUserDto mssUserDto) {
        var finColl = new FinancialColleague();
        finColl.setEmail(mssUserDto.getEmail());
        finColl.setFirstName(mssUserDto.getFirstName());
        finColl.setLastName(mssUserDto.getLastName());
        finColl.setPhoneNumber(mssUserDto.getPhoneNumber());
        finColl.setPhoneNumber(mssUserDto.getPhoneNumber());
        finColl.setActive(false);
        finColl.setRoles("ROLE_FINANCIAL");
        finColl.setRegistrationDate(LocalDateTime.now());
        return finColl;
    }
}

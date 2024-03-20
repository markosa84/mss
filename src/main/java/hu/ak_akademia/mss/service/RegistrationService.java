package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.dto.*;
import hu.ak_akademia.mss.login_security_service.PasswordEncryption;
import hu.ak_akademia.mss.model.AreaOfExpertise;
import hu.ak_akademia.mss.model.Language;
import hu.ak_akademia.mss.model.user.MssUser;
import hu.ak_akademia.mss.mss_user_validation_process.AssistantValidationRegistrationProcess;
import hu.ak_akademia.mss.mss_user_validation_process.ClientValidationRegistrationProcess;
import hu.ak_akademia.mss.mss_user_validation_process.DoctorValidationRegistrationProcess;
import hu.ak_akademia.mss.mss_user_validation_process.FinancialColleagueValidationRegistrationProcess;
import hu.ak_akademia.mss.repository.AreaOfExpertiseRepository;
import hu.ak_akademia.mss.repository.GenderRepository;
import hu.ak_akademia.mss.repository.LanguageRepository;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class RegistrationService {

    @Autowired
    private MSSUserRepository mssUserRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private RegistrationVerificationService registrationVerificationService;

    @Autowired
    private AreaOfExpertiseRepository areaOfExpertiseRepository;

    public void save(MssUser mssUsers) {
        mssUserRepository.save(mssUsers);
    }

    public void delete(MssUser mssUsers) {
        mssUserRepository.delete(mssUsers);
    }

    public ResponseEntity<Collection<String>> validateRegistrationClient(ClientRegistrationDto regClient) throws MessagingException {
        var clientValProcess = new ClientValidationRegistrationProcess(this, mssUserRepository, registrationVerificationService);
        return clientValProcess.controlMssUserRegistrationProcess(regClient);
    }

    public ResponseEntity<Collection<String>> validateRegistrationDoctor(DoctorRegistrationDto regDoctor) throws MessagingException {
        var doctorValProcess = new DoctorValidationRegistrationProcess(this, mssUserRepository, registrationVerificationService);
        return doctorValProcess.controlMssUserRegistrationProcess(regDoctor);
    }

    public ResponseEntity<Collection<String>> validateRegistrationAssistant(AssistantRegistrationDto regAssistant) throws MessagingException {
        var assistantValProcess = new AssistantValidationRegistrationProcess(this, mssUserRepository, registrationVerificationService);
        return assistantValProcess.controlMssUserRegistrationProcess(regAssistant);
    }

    public ResponseEntity<Collection<String>> validateRegistrationFinancial(FinancialColleagueRegistrationDto regFinancial) throws MessagingException {
        var assistantValProcess = new FinancialColleagueValidationRegistrationProcess(this, mssUserRepository, registrationVerificationService);
        return assistantValProcess.controlMssUserRegistrationProcess(regFinancial);
    }

    public List<Language> getLanguages(List<Integer> languageId) {
        return languageRepository.findAllById(languageId);
    }

    public List<AreaOfExpertise> getAreaOfExpertises(List<Integer> areaOfExpertiseId) {
        return areaOfExpertiseRepository.findAllById(areaOfExpertiseId);
    }

    public void encryptPassword(MssUser mssUsers) {
        mssUsers.setPassword(new PasswordEncryption(mssUsers.getPassword()).encryptWithMD5());
    }

    public List<LanguageDto> generateLanguageDto() {
        return languageRepository.findAll().stream().map(l -> new LanguageDto(l.getId(), l.getName())).toList();
    }

    public List<GenderDto> generateGenderDto() {
        return genderRepository.findAll().stream().map(g -> new GenderDto(g.getId(), g.getGender())).toList();
    }

    public boolean isUniqueEmail(String email) {
        return mssUserRepository.findByEmail(email).isPresent();
    }

}

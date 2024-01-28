package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.dto.ClientRegistrationDto;
import hu.ak_akademia.mss.dto.DoctorRegistrationDto;
import hu.ak_akademia.mss.dto.GenderDto;
import hu.ak_akademia.mss.dto.LanguageDto;
import hu.ak_akademia.mss.login_security_service.PasswordEncryption;
import hu.ak_akademia.mss.model.AreaOfExpertise;
import hu.ak_akademia.mss.model.Gender;
import hu.ak_akademia.mss.model.Language;
import hu.ak_akademia.mss.model.user.Assistant;
import hu.ak_akademia.mss.model.user.Doctor;
import hu.ak_akademia.mss.model.user.FinancialColleague;
import hu.ak_akademia.mss.model.user.MssUser;
import hu.ak_akademia.mss.mss_user_validation_process.ClientValidationRegistrationProcess;
import hu.ak_akademia.mss.repository.AreaOfExpertiseRepository;
import hu.ak_akademia.mss.repository.GenderRepository;
import hu.ak_akademia.mss.repository.LanguageRepository;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;
import hu.ak_akademia.mss.service.validators.CompositeAssistantValidator;
import hu.ak_akademia.mss.service.validators.CompositeColleagueValidator;
import hu.ak_akademia.mss.service.validators.CompositeDoctorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public Map<String, String> testMSSUserData(Assistant assistant) {
        var assistantValidator = new CompositeAssistantValidator(mssUserRepository);
        assistantValidator.validate(assistant);
        return assistantValidator.getValidatorErrorList();
    }

    public Map<String, String> testMSSUserData(Doctor doctor, String passwordAgain) {
        var doctorValidator = new CompositeDoctorValidator(mssUserRepository);
        doctorValidator.validate(doctor);
        return doctorValidator.getValidatorErrorList();
    }

    public Map<String, String> testMSSUserData(FinancialColleague colleague) {
        var colleagueValidator = new CompositeColleagueValidator(mssUserRepository);
        colleagueValidator.validate(colleague);
        return colleagueValidator.getValidatorErrorList();
    }

    public ResponseEntity<Collection<String>> validateRegistrationClient(ClientRegistrationDto regClient) throws MessagingException {
        var clientValProcess = new ClientValidationRegistrationProcess(this, mssUserRepository, registrationVerificationService);
        return clientValProcess.controlMssUserRegistrationProcess(regClient);
    }
    public ResponseEntity<Collection<String>> validateRegistrationDoctor(DoctorRegistrationDto regDoctor) throws MessagingException {
        var clientValProcess = new ClientValidationRegistrationProcess(this, mssUserRepository, registrationVerificationService);
        return clientValProcess.controlMssUserRegistrationProcess(regDoctor);
    }

    public List<Language> getLanguages(List<Integer> languageId) {
        return languageRepository.findAllById(languageId);
    }

    public MssUser getUser(String email, String password) throws IncorrectEnteredDataException {
        var passwordEncryption = new PasswordEncryption(password);
        return mssUserRepository.getMSSUserByEmail(email, passwordEncryption.encryptWithMD5()).orElseThrow(() -> new IncorrectEnteredDataException("loginError", "Incorrect password or email!"));
    }

    public MssUser getLoggedInUser(String email) {
        return mssUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public List<Gender> getAllGender() {
        return genderRepository.findAll();
    }

    public List<AreaOfExpertise> getAllAreaOfExpertises() {
        return areaOfExpertiseRepository.findAll();
    }

    public List<AreaOfExpertise> getAreaOfExpertises(List<Integer> areaOfExpertiseId) {
        return areaOfExpertiseRepository.findAllById(areaOfExpertiseId);
    }

    public List<Language> getLanguages() {
        return languageRepository.findAll();
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

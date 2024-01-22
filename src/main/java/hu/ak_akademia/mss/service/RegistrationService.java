package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.dto.ClientRegistrationDto;
import hu.ak_akademia.mss.dto.DoctorRegistrationDto;
import hu.ak_akademia.mss.dto.GenderDto;
import hu.ak_akademia.mss.dto.LanguageDto;
import hu.ak_akademia.mss.login_security_service.PasswordEncryption;
import hu.ak_akademia.mss.model.AreaOfExpertise;
import hu.ak_akademia.mss.model.Gender;
import hu.ak_akademia.mss.model.Language;
import hu.ak_akademia.mss.model.user.*;
import hu.ak_akademia.mss.repository.AreaOfExpertiseRepository;
import hu.ak_akademia.mss.repository.GenderRepository;
import hu.ak_akademia.mss.repository.LanguageRepository;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;
import hu.ak_akademia.mss.service.validators.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.*;

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

    public ResponseEntity<Collection<String>> validateClientInRegistrationProcess(ClientRegistrationDto RegClient) throws MessagingException {
        var client = mappingRegClientToMssUserClient(RegClient);
        var clientValidator = new CompositeClientValidator(mssUserRepository);
        clientValidator.validate(client);
        if (clientValidator.getValidatorErrorList().isEmpty()) {
            encryptPassword(client);
            save(client);
            registrationVerificationService.performRegistrationVerification(client);
            return ResponseEntity.ok().body(Collections.emptyList());
        }
        return ResponseEntity.status(403).body(clientValidator.getValidatorErrorList().values());
    }

    public ResponseEntity<Collection<String>> validateDoctorInRegistrationProcess(DoctorRegistrationDto RegDoctor) throws MessagingException {
        var doctor = mappingRegDoctorToMssUserClient(RegDoctor);
        var doctorValidator = new CompositeDoctorValidator(mssUserRepository);
        doctorValidator.validate(doctor);
        if (doctorValidator.getValidatorErrorList().isEmpty()) {
            encryptPassword(doctor);
            save(doctor);
            registrationVerificationService.performRegistrationVerification(doctor);
            return ResponseEntity.ok().body(Collections.emptyList());
        }
        return  ResponseEntity.status(403).body(doctorValidator.getValidatorErrorList().values());
    }

    private Client mappingRegClientToMssUserClient(ClientRegistrationDto regClient) {
        var client = new Client();
        client.setEmail(regClient.getEmail());
        client.setPassword(regClient.getPassword());
        client.setActive(false);
        client.setRegistrationDate(LocalDateTime.now());
        client.setDateOfBirth(regClient.getDateOfBirth());
        client.setMothersName(regClient.getMothersName());
        client.setPlaceOfBirth(regClient.getPlaceOfBirth());
        client.setTAJNumber(regClient.getTajNumber());
        client.setFirstName(regClient.getFirstName());
        client.setLastName(regClient.getLastName());
        client.setLanguages(getLanguages(regClient.getLanguageId()));
        client.setGender(regClient.getGenderId());
        client.setRoles("ROLE_CLIENT");
        client.setPhoneNumber(regClient.getPhoneNumber());
        return client;
    }

    private Doctor mappingRegDoctorToMssUserClient(DoctorRegistrationDto regDoctor) {
        var doctor = new Doctor();
        doctor.setEmail(regDoctor.getEmail());
        doctor.setPassword(regDoctor.getPassword());
        doctor.setActive(false);
        doctor.setRegistrationDate(LocalDateTime.now());
        doctor.setFirstName(regDoctor.getFirstName());
        doctor.setLastName(regDoctor.getLastName());
        doctor.setLanguages(getLanguages(regDoctor.getLanguageId()));
        doctor.setAreaOfExpertise(getAreaOfExpertises(regDoctor.getAreaOfExpertiseId()));
        doctor.setGender(regDoctor.getGenderId());
        doctor.setRoles("ROLE_DOCTOR");
        doctor.setPhoneNumber(regDoctor.getPhoneNumber());
        return doctor;
    }

    private List<Language> getLanguages(List<Integer> languageId) {
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

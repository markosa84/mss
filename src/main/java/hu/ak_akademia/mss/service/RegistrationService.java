package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.dto.GenderDto;
import hu.ak_akademia.mss.dto.LanguageAndGenderToRegistrationDto;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    private AreaOfExpertiseRepository areaOfExpertiseRepository;

    public void save(MssUser mssUsers) {
        mssUserRepository.save(mssUsers);
    }

    public void delete(MssUser mssUsers) {
        mssUserRepository.delete(mssUsers);
    }

    public Map<String, String> comparePassword(MssUser mssUser, String passwordAgain) {
        return !mssUser.getPassword().equals(passwordAgain)
                ? Collections.singletonMap("samePasswordError", "Incorrect! The two passwords must be the same")
                : Collections.emptyMap();
    }

    public Map<String, String> testMSSUserData(Assistant assistant) {
        var assistantValidator = new CompositeAssistantValidator(this);
        assistantValidator.validate(assistant);
        return assistantValidator.getValidatorErrorList();
    }

    public Map<String, String> testMSSUserData(Doctor doctor, String passwordAgain) {
        var doctorValidator = new CompositeDoctorValidator(this);
        doctorValidator.validate(doctor);

        var instance = MSSUserValidatorFactory.getInstance();
        instance.collectValidationError(new ConfirmationPasswordValidator(), doctor.getPassword(), passwordAgain, doctorValidator.getValidatorErrorList());

        return doctorValidator.getValidatorErrorList();
    }

    public Map<String, String> testMSSUserData(FinancialColleague colleague) {
        var colleagueValidator = new CompositeColleagueValidator(this);
        colleagueValidator.validate(colleague);
        return colleagueValidator.getValidatorErrorList();
    }

    public Map<String, String> testMSSUserData(Client client, String passwordAgain) {
        var clientValidator = new CompositeClientValidator(this);
        clientValidator.validate(client);

        var clientCompareValidator = new ClientCompareValidator(this, clientValidator.getValidatorErrorList());
        clientCompareValidator.validate(client.getPassword(), passwordAgain);

        return clientValidator.getValidatorErrorList();
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

    public List<Language> getLanguages() {
        return languageRepository.findAll();
    }

    public boolean checkUniqueEmail(String email) {
        return mssUserRepository.findByEmail(email).isPresent();
    }

    public void encryptPassword(MssUser mssUsers) {
        mssUsers.setPassword(new PasswordEncryption(mssUsers.getPassword()).encryptWithMD5());
    }

    public LanguageAndGenderToRegistrationDto provideLanguageAndGenderDto() {
        return new LanguageAndGenderToRegistrationDto(generateGenderDto(), generateLanguageDto());
    }

    private List<LanguageDto> generateLanguageDto() {
        return languageRepository.findAll().stream().map(l -> new LanguageDto(l.getId(), l.getName())).toList();
    }

    private List<GenderDto> generateGenderDto() {
        return genderRepository.findAll().stream().map(g -> new GenderDto(g.getId(), g.getGender())).toList();
    }

}

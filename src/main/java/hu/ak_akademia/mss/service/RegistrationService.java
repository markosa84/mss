package hu.ak_akademia.mss.service;

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
        encryptPassword(mssUsers);
        mssUserRepository.save(mssUsers);
    }

    public Map<String, String> testMSSUserData(Assistant assistant) {
        var assistantValidator = new CompositeAssistantValidator(checkUniqueEmail(assistant.getEmail()));
        List<Validator<Assistant>> allAssistantValidator = MSSUserValidatorFactory.getInstance().getAllAssistantValidators();
        assistantValidator.addValidators(allAssistantValidator);
        assistantValidator.validate(assistant);
        return assistantValidator.getValidatorErrorList();
    }

    public Map<String, String> testMSSUserData(Doctor doctor) {
        var doctorValidator = new CompositeDoctorValidator(checkUniqueEmail(doctor.getEmail()));
        List<Validator<Doctor>> allDoctorValidators = MSSUserValidatorFactory.getInstance().getAllDoctorValidators();
        doctorValidator.addValidators(allDoctorValidators);
        doctorValidator.validate(doctor);
        return doctorValidator.getValidatorErrorList();
    }

    public Map<String, String> testMSSUserData(FinancialColleague colleague) {
        var colleaugeValidator = new CompositeColleagueValidator(checkUniqueEmail(colleague.getEmail()));
        List<Validator<FinancialColleague>> allDoctorValidators = MSSUserValidatorFactory.getInstance().getAllColleagueValidators();
        colleaugeValidator.addValidators(allDoctorValidators);
        colleaugeValidator.validate(colleague);
        return colleaugeValidator.getValidatorErrorList();
    }

    public Map<String, String> testMSSUserData(Client client) {
        var clientValidator = new CompositeClientValidator();
        clientValidator.validate(client);
        return clientValidator.getValidatorErrorList();
    }

    public List<? extends MssUser> getGivenMssUser(String client) {
        return mssUserRepository.getAllGivenUserType(client).orElseThrow(null);
    }

    public MssUser getUser(String email, String password) throws IncorrectEnteredDataException {
        return mssUserRepository.getMSSUserByEmail(email, password).orElseThrow(() -> new IncorrectEnteredDataException("loginError", "Incorrect password or email!"));
    }

    public MssUser getLoggedInUser(String email) {
        return mssUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Not found user"));
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

    private void encryptPassword(MssUser mssUsers) {
        mssUsers.setPassword(new PasswordEncryption(mssUsers.getPassword()).encryptWithMD5());
    }

}

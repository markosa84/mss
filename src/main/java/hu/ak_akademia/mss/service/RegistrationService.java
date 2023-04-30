package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.AreaOfExpertise;
import hu.ak_akademia.mss.model.Gender;
import hu.ak_akademia.mss.model.Language;
import hu.ak_akademia.mss.model.user.*;
import hu.ak_akademia.mss.repository.AreaOfExpertiseRepository;
import hu.ak_akademia.mss.repository.GenderTableRepository;
import hu.ak_akademia.mss.repository.LanguageTableRepository;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;
import hu.ak_akademia.mss.service.validators.CompositeClientValidator;
import hu.ak_akademia.mss.service.validators.MSSUserValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class RegistrationService {

    private MSSUserRepository mssUserRepository;

    @Autowired
    private GenderTableRepository genderRepository;
    @Autowired
    private AreaOfExpertiseRepository areaOfExpertiseRepository;

    @Autowired
    private LanguageTableRepository languageRepository;

    @Autowired
    public void setMssUserRepository(MSSUserRepository mssUserRepository) {
        this.mssUserRepository = mssUserRepository;
    }

    public void save(MssUser mssUsers) {
        mssUsers.setRoles("ROLE_CLIENT");
        encryptPassword(mssUsers);
        mssUserRepository.save(mssUsers);
    }

    public Map<String, String> testMSSUserData(Assistant assistant) {
        return Collections.emptyMap();
    }

    public Map<String, String> testMSSUserData(Doctor doctor) {
        return Collections.emptyMap();
    }

    public Map<String, String> testMSSUserData(FinancialColleague colleague) {
        return Collections.emptyMap();
    }

    public Map<String, String> testMSSUserData(Client client) {
        var clientValidator = new CompositeClientValidator();
        clientValidator.addValidators(MSSUserValidatorFactory.getInstance().getAllClientValidators());
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

    private void encryptPassword(MssUser mssUsers) {
        mssUsers.setPassword(new PasswordEncryption(mssUsers.getPassword()).encryptWithMD5());
    }
}

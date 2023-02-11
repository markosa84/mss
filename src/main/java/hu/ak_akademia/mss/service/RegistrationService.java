package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.user.MssUser;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;
import hu.ak_akademia.mss.service.validators.CompositeMSSUserValidator;
import hu.ak_akademia.mss.service.validators.MSSUserValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RegistrationService {

    private MSSUserRepository mssUserRepository;

    @Autowired
    public void setMssUserRepository(MSSUserRepository mssUserRepository) {
        this.mssUserRepository = mssUserRepository;
    }

    public void save(MssUser mssUsers) {
        encryptPassword(mssUsers);
        mssUserRepository.save(mssUsers);
    }

    public Map<String, String> testMSSUserData(MssUser mssUsers) {
        var mssUserValidator = new CompositeMSSUserValidator();
        mssUserValidator.addValidators(MSSUserValidatorFactory.getInstance().getAllMSSUserValidators());
        mssUserValidator.validate(mssUsers);
        return mssUserValidator.getValidatorErrorList();
    }

    private void encryptPassword(MssUser mssUsers) {
        mssUsers.setPassword(new PasswordEncryption(mssUsers.getPassword()).encryptWithMD5());
    }

    public List<? extends MssUser> getGivenMssUser(String client) {
        return mssUserRepository.getAllGivenUserType(client).orElseThrow(null);
    }

    public MssUser authentication(String email, String password) throws IncorrectEnteredDataException {
        return mssUserRepository.getMSSUserByEmail(email, password).orElseThrow(() -> new IncorrectEnteredDataException("loginError", "Incorrect password or email!"));
    }
}

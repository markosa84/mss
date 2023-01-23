package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.MssUsers;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import hu.ak_akademia.mss.service.validators.CompositeMSSUserValidator;
import hu.ak_akademia.mss.service.validators.MSSUserValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RegistrationService {

    private MSSUserRepository mssUserRepository;

    @Autowired
    public void setMssUserRepository(MSSUserRepository mssUserRepository) {
        this.mssUserRepository = mssUserRepository;
    }

    public void save(MssUsers mssUsers) {
        encryptPassword(mssUsers);
        mssUserRepository.save(mssUsers);
    }

    public Map<String, String> testMSSUserData(MssUsers mssUsers) {
        var mssUserValidator = new CompositeMSSUserValidator();
        mssUserValidator.addValidators(MSSUserValidatorFactory.getInstance().getAllMSSUserValidators());
        mssUserValidator.validate(mssUsers);
        return mssUserValidator.getValidatorErrorList();
    }

    private void encryptPassword(MssUsers mssUsers) {
        mssUsers.setPassword(new PasswordEncryption().encrypt(mssUsers));
    }
}

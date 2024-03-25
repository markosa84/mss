package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.dto.RegistrationCodeDto;
import hu.ak_akademia.mss.model.RegistrationVerificationCode;
import hu.ak_akademia.mss.model.user.MssUser;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import hu.ak_akademia.mss.repository.RegistrationVerificationRepository;
import hu.ak_akademia.mss.service.exceptions.InvalidActivationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RegistrationVerificationCodeService {
    private final RegistrationVerificationRepository registrationVerificationRepository;
    private final MSSUserRepository mssUserRepository;

    @Autowired
    public RegistrationVerificationCodeService(RegistrationVerificationRepository registrationVerificationRepository, MSSUserRepository mssUserRepository) {
        this.registrationVerificationRepository = registrationVerificationRepository;
        this.mssUserRepository = mssUserRepository;
    }

    public void saveVerificationCode(RegistrationVerificationCode verificationCode) {
        registrationVerificationRepository.save(verificationCode);
    }

    public MssUser findUserByVerificationCode(String verificationCode) {
        return registrationVerificationRepository.findByVerificationCode(verificationCode).orElseThrow().getUser();
    }

    public boolean isRegistrationCodeValid(String verificationCode) {
        Optional<RegistrationVerificationCode> code = registrationVerificationRepository.findByVerificationCode(verificationCode);
        return code.map(r -> r.getExpiryDate().plusDays(1).isAfter(LocalDate.now())).orElse(false);
    }

    public void activateUser(RegistrationCodeDto verificationCode) {
        if (!isRegistrationCodeValid(verificationCode.code())) {
            throw new InvalidActivationException("The activation token is invalid. It may have been expired.");
        }
        MssUser user = findUserByVerificationCode(verificationCode.code());
        user.setActive(true);
        mssUserRepository.save(user);
    }
}



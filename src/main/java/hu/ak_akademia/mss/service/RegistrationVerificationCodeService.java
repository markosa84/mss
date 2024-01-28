package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.RegistrationVerificationCode;
import hu.ak_akademia.mss.model.user.MssUser;
import hu.ak_akademia.mss.repository.RegistrationVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RegistrationVerificationCodeService {
    private final RegistrationVerificationRepository registrationVerificationRepository;

    @Autowired
    public RegistrationVerificationCodeService(RegistrationVerificationRepository registrationVerificationRepository) {
        this.registrationVerificationRepository = registrationVerificationRepository;
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
}



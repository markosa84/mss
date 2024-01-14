package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.user.MssUser;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class RegistrationVerificationService {

    private EmailService emailService;

    //
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void performRegistrationVerification(@NotNull MssUser user) throws MessagingException {
        emailService.sendRegistrationEmail(user.getEmail());
        emailService.saveRegistrationVerificationCode(user);
    }
}


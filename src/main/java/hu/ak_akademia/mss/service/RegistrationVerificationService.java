package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.user.MssUser;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.mail.MessagingException;

@Service
public class RegistrationVerificationService {

    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public String performRegistrationVerification(@NotNull MssUser user, @NotNull Model model) throws MessagingException {
        emailService.sendRegistrationEmail(user.getEmail());
        emailService.saveRegistrationVerificationCode(user);
        model.addAttribute("message", "Regisztráció sikeres!");
        model.addAttribute("first_name", user.getFirstName());
        model.addAttribute("last_name", user.getLastName());
        return "success_registration";
    }
}


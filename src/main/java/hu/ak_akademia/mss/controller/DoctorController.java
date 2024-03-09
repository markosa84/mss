package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.dto.DoctorRegistrationDto;
import hu.ak_akademia.mss.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import java.util.Collection;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/registration")
    public ResponseEntity<Collection<String>> registrationFormDoctor(DoctorRegistrationDto registrationDto) {
        try {
            return registrationService.validateRegistrationDoctor(registrationDto);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.dto.AssistantRegistrationDto;
import hu.ak_akademia.mss.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.Collection;

@RestController
@RequestMapping("/assistant")
public class AssistantController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/registration")
    public ResponseEntity<Collection<String>> registrationFormAssistant(AssistantRegistrationDto registrationDto) {
        try {
            return registrationService.validateRegistrationAssistant(registrationDto);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}

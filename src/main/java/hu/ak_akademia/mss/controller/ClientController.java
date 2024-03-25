package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.dto.ClientRegistrationDto;
import hu.ak_akademia.mss.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.Collection;

@RestController
@RequestMapping("/register/client")
public class ClientController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/registration")
    public ResponseEntity<Collection<String>> registrationFormClient(@RequestBody ClientRegistrationDto registrationClient) throws MessagingException {
        return registrationService.validateRegistrationClient(registrationClient);
    }

}

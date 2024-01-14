package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.dto.ClientRegistrationDto;
import hu.ak_akademia.mss.dto.GenderDto;
import hu.ak_akademia.mss.dto.LanguageDto;
import hu.ak_akademia.mss.service.RegistrationService;
import hu.ak_akademia.mss.service.RegistrationVerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/register")
public class ClientController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private RegistrationVerificationCodeService registrationVerificationCodeService;


    @GetMapping("/languages")
    public List<LanguageDto> languageRegistration() {
        return registrationService.generateLanguageDto();
    }

    @GetMapping("/genders")
    public List<GenderDto> genderRegistration() {
        return registrationService.generateGenderDto();
    }

    @PostMapping("/client")
    public ResponseEntity<Collection<String>> registrationForm(@RequestBody ClientRegistrationDto registrationClient) {
        try {
            return registrationService.validateClientInRegistrationProcess(registrationClient);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<Collection<String>> registrationVerifyCode(String code) {
        if (!registrationVerificationCodeService.isRegistrationCodeValid(code)) {
            return ResponseEntity.of(Optional.of(List.of("Not Ok")));
        }
        registrationVerificationCodeService.findUserByVerificationCode(code);
        return ResponseEntity.of(Optional.of(List.of("Ok")));
    }

}

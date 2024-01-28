package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.dto.ClientRegistrationDto;
import hu.ak_akademia.mss.dto.GenderDto;
import hu.ak_akademia.mss.dto.LanguageDto;
import hu.ak_akademia.mss.dto.UniqueEmailDto;
import hu.ak_akademia.mss.service.RegistrationService;
import hu.ak_akademia.mss.service.RegistrationVerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/register")
public class RegistrationController {

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
            return registrationService.validateRegistrationClient(registrationClient);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/unique/email")
    public ResponseEntity<Boolean> isUniqueEmail(@RequestBody UniqueEmailDto uniqueEmailDto) {
        return ResponseEntity.ok().body(registrationService.isUniqueEmail(uniqueEmailDto.email()));
    }

    @PostMapping("/verify")
    public ResponseEntity<String> registrationVerifyCode(@RequestBody Map<String, String> payload) {
        String code = payload.get("code");
        if (!registrationVerificationCodeService.isRegistrationCodeValid(code)) {
            return ResponseEntity.status(400).build();
        }
        registrationVerificationCodeService.findUserByVerificationCode(code);
        return ResponseEntity.status(200).build();
    }
}

package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.dto.*;
import hu.ak_akademia.mss.service.RegistrationService;
import hu.ak_akademia.mss.service.RegistrationVerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
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

    @PostMapping("/doctor")
    public ResponseEntity<Collection<String>> registrationWithDoctor(@RequestBody DoctorRegistrationDto registrationDoctor) {
        try {
            return registrationService.validateDoctorInRegistrationProcess(registrationDoctor);
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
            return ResponseEntity.status(400).body(null);
        }
        registrationVerificationCodeService.findUserByVerificationCode(code);
        return ResponseEntity.status(200).body(null);
    }

}

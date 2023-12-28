package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.dto.ClientRegistrationDto;
import hu.ak_akademia.mss.dto.GenderDto;
import hu.ak_akademia.mss.dto.LanguageDto;
import hu.ak_akademia.mss.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/register")
public class ClientController {

    @Autowired
    private RegistrationService registrationService;

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
        return registrationService.validateClientInRegistrationProcess(registrationClient);
    }


}

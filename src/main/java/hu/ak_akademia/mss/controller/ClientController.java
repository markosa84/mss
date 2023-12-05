package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.dto.LanguageAndGenderToRegistrationDto;
import hu.ak_akademia.mss.model.user.Client;
import hu.ak_akademia.mss.service.RegistrationService;
import hu.ak_akademia.mss.service.RegistrationVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.Map;

@RestController
@RequestMapping("/register")
public class ClientController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private RegistrationVerificationService registrationVerificationService;


    @GetMapping()
    public LanguageAndGenderToRegistrationDto registration(Client client, Model model) {
        return registrationService.provideLanguageAndGenderDto();
    }

    @PostMapping("/client")
    public String registrationForm(Client client, String passwordAgain, Model model) throws MessagingException {
        Map<String, String> errorList = registrationService.testMSSUserData(client, passwordAgain);
        if (errorList.isEmpty()) {
            client.setRoles("ROLE_CLIENT");
            registrationService.encryptPassword(client);
            registrationService.save(client);
            return registrationVerificationService.performRegistrationVerification(client, model);
        }
        model.addAttribute("genderList", registrationService.getAllGender());
        model.addAttribute("languageList", registrationService.getLanguages());
        model.addAttribute("passwordAgain", "");
        model.addAllAttributes(errorList);
        return "registration";
    }


}

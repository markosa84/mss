package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.model.Gender;
import hu.ak_akademia.mss.model.user.Assistant;
import hu.ak_akademia.mss.service.RegistrationService;
import hu.ak_akademia.mss.service.RegistrationVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assistant")
public class AssistantController {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private RegistrationVerificationService registrationVerificationService;

    @GetMapping("/registration")
    public List<Gender> assistant_registration(Assistant assistant, Model model) {
        model.addAttribute("genderList", registrationService.getAllGender());
        return registrationService.getAllGender();
    }

}

package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.model.user.FinancialColleague;
import hu.ak_akademia.mss.service.RegistrationService;
import hu.ak_akademia.mss.service.RegistrationVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/financialColleague")
public class FinancialColleagueController {

    @Autowired
    RegistrationService registrationService;
    @Autowired
    private RegistrationVerificationService registrationVerificationService;
    @GetMapping("/registration")
    public String financialColleague_registration(FinancialColleague financialColleague, Model model) {
        model.addAttribute("genderList", registrationService.getAllGender());
        return "financialColleague_registration";
    }

}

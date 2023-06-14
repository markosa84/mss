package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.model.user.FinancialColleague;
import hu.ak_akademia.mss.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/register")
public class FinancialColleagueController {

    @Autowired
    RegistrationService registrationService;

    @GetMapping("/financialColleague")
    public String financialColleague_registration(FinancialColleague financialColleague, Model model) {
        model.addAttribute("genderList", registrationService.getAllGender());
        return "financialColleague_registration";
    }

    @PostMapping("/financialColleague")
    public String financialColleagueRegistrationForm(FinancialColleague financialColleague, Model model) {
        Map<String, String> errorList = registrationService.testMSSUserData(financialColleague);
        if (errorList.isEmpty()) {
            financialColleague.setRoles("ROLE_FINANCIALCOLLEAGUE");
            registrationService.save(financialColleague);
            return "redirect:/";
        }
        model.addAttribute("genderList", registrationService.getAllGender());
        model.addAllAttributes(errorList);
        return "financialColleague_registration";
    }
}

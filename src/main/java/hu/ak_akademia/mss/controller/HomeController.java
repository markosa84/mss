package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.model.user.*;
import hu.ak_akademia.mss.service.LoginService;
import hu.ak_akademia.mss.service.PasswordEncryption;
import hu.ak_akademia.mss.service.RegistrationService;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {

    private LoginService loginService;
    private RegistrationService registrationService;


    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String index() {
        return "/index";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        var currentUser = registrationService.getLoggedInUser(principal.getName());
        model.addAttribute("currentUser", currentUser.getFirstName() + " " + currentUser.getLastName());
        return "home";
    }

    @ExceptionHandler(value = RuntimeException.class)
    public String error(RuntimeException e, Model model) {
        model.addAttribute("exception", e.getMessage());
        return "error";
    }

//    ************************************************************************************************************

    @GetMapping("/login")
    public String login(Model model, String error) {
        if (error != null) {
            model.addAttribute("errorMsg", "Incorrect username or password!");
        }
        return "/login";
    }

//    @PostMapping("/login")
//    public String loginProcess(Model model, @RequestParam String email, @RequestParam String password) {
//        var currentPassword = new PasswordEncryption(password).encryptWithMD5();
//        try {
//            MssUser user = registrationService.getUser(email, currentPassword);
//        } catch (IncorrectEnteredDataException e) {
//            System.out.println(e.getErrorMessage());
//            model.addAttribute("loginError", e.getErrorMessage());
//            return "login";
//        }
//        return "home";
//    }

//    **************************************************************************************************************

    @GetMapping("/register")
    public String registration(Client client, Model model) {
        model.addAttribute("genderList", registrationService.getAllGender());
        model.addAttribute("languageList", registrationService.getLanguages());
        return "registration";
    }

    @PostMapping("/register/client")
    public String registrationForm(Client client, Model model) {
        Map<String, String> errorList = registrationService.testMSSUserData(client);
        if (errorList.isEmpty()) {
            client.setRoles("ROLE_CLIENT");
            registrationService.save(client);
            return "index";
        }
        model.addAttribute("genderList", registrationService.getAllGender());
        model.addAttribute("languageList", registrationService.getLanguages());
        model.addAllAttributes(errorList);
        return "registration";
    }

    //**********************************************************************************************************
    @GetMapping("/register/assistant")
    public String assistant_registration(Assistant assistant, Model model) {
        model.addAttribute("genderList", registrationService.getAllGender());
        return "assistant_registration";
    }

    @PostMapping("/register/assistant")
    public String assistantRegistrationForm(Assistant assistant, Model model) {
        Map<String, String> errorList = registrationService.testMSSUserData(assistant);
        if (errorList.isEmpty()) {
            registrationService.save(assistant);
            return "index";
        }
        model.addAttribute("genderList", registrationService.getAllGender());
        model.addAllAttributes(errorList);
        return "assistant_registration";
    }

    //**********************************************************************************************************
    @GetMapping("/register/doctor")
    public String doctor_registration(Doctor doctor, Model model) {
        model.addAttribute("genderList", registrationService.getAllGender());
        model.addAttribute("areaOfExpertiseList", registrationService.getAllAreaOfExpertises());
        return "doctor_registration";
    }

    @PostMapping("/register/doctor")
    public String doctorRegistrationForm(Doctor doctor, Model model) {
        Map<String, String> errorList = registrationService.testMSSUserData(doctor);
        if (errorList.isEmpty()) {
            doctor.setRoles("ROLE_DOCTOR");
            registrationService.save(doctor);
            return "index";
        }
        model.addAttribute("genderList", registrationService.getAllGender());
        model.addAttribute("areaOfExpertiseList", registrationService.getAllAreaOfExpertises());
        model.addAllAttributes(errorList);
        return "doctor_registration";
    }


    //**********************************************************************************************************
    @GetMapping("/register/financialColleague")
    public String financialColleague_registration(FinancialColleague financialColleague, Model model) {
        model.addAttribute("genderList", registrationService.getAllGender());
        return "financialColleague_registration";
    }

    @PostMapping("/register/financialColleague")
    public String financialColleagueRegistrationForm(FinancialColleague financialColleague, Model model) {
        Map<String, String> errorList = registrationService.testMSSUserData(financialColleague);
        if (errorList.isEmpty()) {
            registrationService.save(financialColleague);
            return "index";
        }
        model.addAttribute("genderList", registrationService.getAllGender());
        model.addAllAttributes(errorList);
        return "financialColleague_registration";
    }

}

package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.model.MssUsers;
import hu.ak_akademia.mss.service.LoginService;
import hu.ak_akademia.mss.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

//    @ExceptionHandler(value = RuntimeException.class)
//    public String error(RuntimeException e, Model model) {
//        model.addAttribute("exception", e.getMessage());
//        return "error";
//    }

//    ************************************************************************************************************

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @PostMapping("/login")
    public String loginRequest(Model model, @RequestParam String email, @RequestParam String password) {
        System.out.println("email: " + email + ", password: " + password);
        // TODO: join the data to the LoginService class.
        return "redirect:/";
    }

//    **************************************************************************************************************

    @GetMapping("/register")
    public String registration(MssUsers mssUsers, Model model) {
        // TODO: join the client object to the RegistrationService;
        return "registration";
    }

    @PostMapping("/register/mssUsers")
    public String registrationForm(MssUsers mssUsers, Model model) {
        Map<String, String> errorList = registrationService.testMSSUserData(mssUsers);
        if (errorList.isEmpty()) {
            registrationService.save(mssUsers);
            return "index";
        }
        model.addAllAttributes(errorList);
        return "registration";
    }
}

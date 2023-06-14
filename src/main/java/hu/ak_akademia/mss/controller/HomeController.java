package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    RegistrationService registrationService;

    @GetMapping
    public String index() {
        return "/index";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        var currentUser = registrationService.getLoggedInUser(principal.getName());
        if (!currentUser.isActive()) {
            model.addAttribute("errorMsg", "Your account exists but is not active yet!");
            return "/login";
        }
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

    @PostMapping("/logout")
    public String logout() {
        return "login";
    }

}

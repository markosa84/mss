package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.model.Client;
import hu.ak_akademia.mss.service.LoginService;
import hu.ak_akademia.mss.service.RegistrationService;
import hu.ak_akademia.mss.service.exceptions.IncorrectEnteredDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        Client client = new Client();
        client.setPhoneNumber("+36303519011");
        client.setLastName("Istv5án");
        client.setPassword("Abc123**");
        registrationService.saveClient(client);
        return "/index";
    }

    @ExceptionHandler(value = IncorrectEnteredDataException.class)
    public String error(IncorrectEnteredDataException e, Model model) {
        model.addAttribute("exception", e.getMessage());
        return "error";
    }

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
    public String registration(Client client) {
        registrationService.saveClient(client);
        // TODO: join the client object to the RegistrationService;
        return "registration";
    }
    //TODO: make registration post mapping controller!

}

package hu.ak_akademia.mss.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.ak_akademia.mss.config.SessionMssUser;
import hu.ak_akademia.mss.model.AreaOfExpertise;
import hu.ak_akademia.mss.service.AreaOfExpertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private AreaOfExpertiseService areaOfExpertiseService;
    @Autowired
    private SessionMssUser sessionMssUser;

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        // Ellenőrizd a felhasználó bejelentkezését itt, majd kérj le szükség esetén szakirányokat
        var currentUser = sessionMssUser.getCurrentMssUser();

        if (currentUser.getRoles().equals("ROLE_ADMIN")) {
            model.addAttribute("name", "");
            model.addAttribute("isEmpty", true);
            return "home_admin";
        }

        if (!currentUser.isActive()) {
            model.addAttribute("errorMsg", "Your account is exist but not active!");
            sessionMssUser.clearCurrentUser();
            return "redirect:/login";
        }

        // Kérj le és adj hozzá szakirányokat a modellhez
        List<AreaOfExpertise> areaOfExpertise = areaOfExpertiseService.getAllAreaOfExpertise();
        areaOfExpertise.sort(Comparator.comparing(AreaOfExpertise::getQualification));
        model.addAttribute("areaOfExpertiseList", areaOfExpertise);

        // Konvertáljuk az adatokat JSON formátumba
        ObjectMapper objectMapper = new ObjectMapper();
        String areaOfExpertiseJson;
        try {
            areaOfExpertiseJson = objectMapper.writeValueAsString(areaOfExpertise);
        } catch (JsonProcessingException e) {
            // Kezeljük a kivételt, ha valami nem stimmel
            e.printStackTrace();
            areaOfExpertiseJson = "[]"; // Üres JSON, ha hiba történik
        }
        model.addAttribute("areaOfExpertiseJson", areaOfExpertiseJson);

        return "home";
    }

    @GetMapping("/login")
    public String login(Model model, String error) {
        if (error != null) {
            model.addAttribute("errorMsg", "Incorrect username or password!");
        }

        return "login";
    }

    @PostMapping("/logout")
    public String logout() {
        return "login";
    }
}

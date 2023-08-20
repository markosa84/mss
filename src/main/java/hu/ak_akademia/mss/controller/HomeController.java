package hu.ak_akademia.mss.controller;


import hu.ak_akademia.mss.config.SessionMssUser;
import hu.ak_akademia.mss.model.AreaOfExpertise;
import hu.ak_akademia.mss.model.user.MssUser;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import hu.ak_akademia.mss.service.AreaOfExpertiseService;
import hu.ak_akademia.mss.service.RegistrationService;
import hu.ak_akademia.mss.service.RegistrationVerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private AreaOfExpertiseService areaOfExpertiseService;
    @Autowired
    private RegistrationVerificationCodeService registrationVerificationCodeService;
    @Autowired
    private SessionMssUser sessionMssUser;
@Autowired
private MSSUserRepository mssUserRepository;
    public HomeController() {
    }

    @GetMapping
    public String index() {
        return "/index";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        var currentUser = sessionMssUser.getCurrentMssUser();
        if (!currentUser.isActive()) {
            model.addAttribute("errorMsg", "Your account is exist but not active!");
            sessionMssUser.setCurrentUser(null);
            return "redirect:/login";
        }
        model.addAttribute("currentUser", currentUser.getFirstName() + " " + currentUser.getLastName());


        List<AreaOfExpertise> areaOfExpertise = areaOfExpertiseService.getAllAreaOfExpertise();
        areaOfExpertise.sort(Comparator.comparing(AreaOfExpertise::getQualification));
        model.addAttribute("areaOfExpertiseList", areaOfExpertise);
        return "home";
    }

    @GetMapping("/doctors")
    public String listDoctorsForArea(@RequestParam("areaId") int areaId, Model model) {
        var currentUser = sessionMssUser.getCurrentMssUser();
        model.addAttribute("currentUser", currentUser.getFirstName() + " " + currentUser.getLastName());

        List<MssUser> doctorsForArea = mssUserRepository.findDoctorsByAreaOfExpertise(areaId);
        model.addAttribute("doctorsForArea", doctorsForArea);

        // Választott szakirány nevének lekérése
        String selectedAreaName = areaOfExpertiseService.getAreaOfExpertiseById(areaId);
        model.addAttribute("selectedAreaName", selectedAreaName);


        return "doctors"; // A válaszban "doctors.html" fájlt használjuk
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
            model.addAttribute("errorMsg", "Incorrect username or password1!");

        }
        return "login";
    }

    @PostMapping("/logout")
    public String logout() {
        return "login";
    }

    // *** regisztrácios kod ellenőrzése*****
    @GetMapping("verify_code")
    public String verifyRegistrationCode(@RequestParam("code") String verificationCode, Model model) {
        if (registrationVerificationCodeService.isValidCode(verificationCode)) {
            if (registrationVerificationCodeService.isRegistrationCodeValid(verificationCode)) {
                MssUser user = registrationVerificationCodeService.findUserByVerificationCode(verificationCode);
                if (user != null) {
                    // regisztrácios kod helyes és a kod ideje nem járt még le
                    user.setActive(true); // isActive mező true-ra állítás
                    registrationService.save(user); // Felhasználó mentése az adatbázisba
                    model.addAttribute("message", "Regisztrációs kód helyes!");
                    model.addAttribute("user_first_name", user.getFirstName());
                    model.addAttribute("user_last_name", user.getLastName());
                    model.addAttribute("loginUrl", "/login");
                    return "registration_verification";
                }
            } else {
                // Regisztrációs kód lejárt
                model.addAttribute("message", "Regisztrációs kód lejárt!");
                return "registration_verification";
            }
        }
        // Érvénytelen regisztrációs kód
        model.addAttribute("message", "Érvénytelen regisztrációs kód!");
        return "registration_verification";
    }
}

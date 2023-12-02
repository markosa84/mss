package hu.ak_akademia.mss.controller;


import hu.ak_akademia.mss.config.SessionMssUser;
import hu.ak_akademia.mss.model.Slot;
import hu.ak_akademia.mss.service.AreaOfExpertiseService;
import hu.ak_akademia.mss.service.DoctorsSchedule;
import hu.ak_akademia.mss.service.DoctorsWorkingHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private AreaOfExpertiseService areaOfExpertiseService;
    @Autowired
    private SessionMssUser sessionMssUser;
    @Autowired
    private DoctorsWorkingHoursService doctorsWorkingHoursService;

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        var currentUser = sessionMssUser.getCurrentMssUser();
        System.out.println(currentUser);
        return "currentUser";
    }

    @GetMapping("/test/endpoint")
    public String test() {
        return "Hello World!! Juppppiiii";
    }

    @PostMapping("/logout")
    public String logout() {
        return "login";
    }

    @GetMapping("/slots/{areaId}")
    public List<Slot> specialArea(@PathVariable int areaId) {
        var areaOfExpertise = areaOfExpertiseService.getAreaById(areaId);
        DoctorsSchedule doctorsSchedule = new DoctorsSchedule(areaOfExpertise);
        return doctorsSchedule.generateSlots(doctorsWorkingHoursService);
    }

}

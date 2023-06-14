package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.model.user.Doctor;
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
public class DoctorController {

    @Autowired
    RegistrationService registrationService;

    @GetMapping("/doctor")
    public String doctor_registration(Doctor doctor, Model model) {
        registrationService.loadAttributes(model);
        model.addAttribute("areaOfExpertiseList", registrationService.getAllAreaOfExpertises());
        return "doctor_registration";
    }

    @PostMapping("/doctor")
    public String doctorRegistrationForm(Doctor doctor, Model model) {
        Map<String, String> errorList = registrationService.testMSSUserData(doctor);
        if (errorList.isEmpty()) {
            doctor.setRoles("ROLE_DOCTOR");
            registrationService.save(doctor);
            return "redirect:/";
        }
        registrationService.loadAttributes(model);
        model.addAttribute("areaOfExpertiseList", registrationService.getAllAreaOfExpertises());
        model.addAllAttributes(errorList);
        return "doctor_registration";
    }
}

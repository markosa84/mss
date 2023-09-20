package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.config.SessionMssUser;
import hu.ak_akademia.mss.model.user.Doctor;
import hu.ak_akademia.mss.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class EditProfilController {

    @Autowired
    private SessionMssUser sessionMssUser;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private Container container;

    @GetMapping("/edit")
    public String edit_profil(Model model) {
        container.setAge("40");
        container.setUser((Doctor) sessionMssUser.getCurrentMssUser());
//        System.out.println(container);
        model.addAttribute("name", container);
        return "edit_profil";
    }

    @PostMapping("/saveData")
    public String editRegistrationData(Container ct, Model model) {
        System.out.println(ct.getUser().getFirstName());
        System.out.println(ct);
//        container.setUser(ct.getUser());
        System.out.println(container.getUser() );
        container.setUser((Doctor) sessionMssUser.getCurrentMssUser());
        container.getUser().setFirstName(ct.getUser().getFirstName());
        System.out.println(container.getUser());
        model.addAttribute("name", container);
        return "edit_profil";
    }


}

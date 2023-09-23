package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.config.SessionMssUser;
import hu.ak_akademia.mss.model.user.Doctor;
import hu.ak_akademia.mss.model.Container;
import hu.ak_akademia.mss.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        container.setUser((Doctor) sessionMssUser.getCurrentMssUser());
        model.addAttribute("container", container);
        return "edit_profil";
    }

    @PostMapping("/saveData")
    public String editRegistrationData(Container ct, Model model) {
        System.out.println(ct.getUser().getFirstName());
        System.out.println(sessionMssUser.getCurrentMssUser());
        System.out.println(container.getUser());
        System.out.println(container);
        model.addAttribute("name", container);
        return "edit_profil";
    }

}

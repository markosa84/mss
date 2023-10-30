package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.config.SessionMssUser;
import hu.ak_akademia.mss.model.user.Client;
import hu.ak_akademia.mss.model.Container;
import hu.ak_akademia.mss.service.EditProfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import java.util.Map;

@Controller
@RequestMapping("/")
public class EditProfilController {

    @Autowired
    private SessionMssUser sessionMssUser;

    @Autowired
    private EditProfilService editProfilService;

    @Autowired
    private Container container;

    /*
    @GetMapping("/edit")
    public String edit_profil(Model model) {
        container.setUser((Doctor) sessionMssUser.getCurrentMssUser());
        model.addAttribute("container", container);
        return "edit_profil";
    }

     */

    /*
    @PostMapping("/saveData")
    public String editRegistrationData(Container ct, Model model) {
        System.out.println(ct.getUser().getFirstName());
        System.out.println(sessionMssUser.getCurrentMssUser());
        System.out.println(container.getUser());
        System.out.println(container);
        model.addAttribute("name", container);
        return "edit_profil";
    }

     */

    @GetMapping("/edit")
    public String edit_profil(Model model) {
        Client user =(Client) sessionMssUser.getCurrentMssUser();
        model.addAttribute("passwordAgain", "");
        model.addAttribute("genderList", editProfilService.getAllGender());
        model.addAttribute("languageList", editProfilService.getLanguages());
        model.addAttribute("client", user);
        return "edit_profil";
    }



    @PostMapping("/saveData")
    public String editRegistrationData(Client client, String passwordAgain, Model model) throws MessagingException {



        Map<String, String> errorList = editProfilService.testMSSUserData(client, passwordAgain);
        if (errorList.isEmpty()) {
            editProfilService.encryptPassword(client);
            editProfilService.save(client);
            return "home";
        }


        model.addAttribute("passwordAgain", "");
        model.addAttribute("genderList", editProfilService.getAllGender());
        model.addAttribute("languageList", editProfilService.getLanguages());
        model.addAllAttributes(errorList);


        return "edit_profil";

    }

}

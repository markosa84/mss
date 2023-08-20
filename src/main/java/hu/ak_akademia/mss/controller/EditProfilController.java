package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.config.SessionMssUser;
import hu.ak_akademia.mss.model.user.Client;
import hu.ak_akademia.mss.model.user.Doctor;
import hu.ak_akademia.mss.model.user.MssUser;
import hu.ak_akademia.mss.service.RegistrationService;
import hu.ak_akademia.mss.service.RegistrationVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.util.Map;

@Controller
@RequestMapping("/")
public class EditProfilController {
    @Autowired
    private SessionMssUser sessionMssUser;

    @Autowired
    private RegistrationService registrationService;

/*
    @GetMapping("/edit")
    public ModelAndView edit_profil(Model model) {
        Client user =(Client) sessionMssUser.getCurrentMssUser();
        String passW ="";
        model.addAttribute("passW", passW);
        model.addAttribute("genderList", registrationService.getAllGender());
        model.addAttribute("languageList", registrationService.getLanguages());
        model.addAttribute("test", "körte");

        ModelAndView mav = new ModelAndView("edit_profil");
        mav.addObject("client", user);
        mav.addObject("passW", passW);
        mav.addObject("test", "hello");


        return mav;
    }

 */

    @GetMapping("/edit")
    public String edit_profil(Model model) {
        Client user =(Client) sessionMssUser.getCurrentMssUser();
        String passW ="";
        model.addAttribute("passW", passW);
        model.addAttribute("genderList", registrationService.getAllGender());
        model.addAttribute("languageList", registrationService.getLanguages());
        model.addAttribute("test", "körte");
        model.addAttribute("client", user);




        return "edit_profil";
    }



    @PostMapping("/saveData")
    public String editRegistrationData(@RequestBody MultiValueMap<String, String> formData, Model model) throws MessagingException {
        //System.out.println(passW2 + " íme");
        //System.out.println(client);
        System.out.println(formData);
        /*
        Map<String, String> errorList = registrationService.testMSSUserData(client);
        if (errorList.isEmpty()) {
            registrationService.encryptPassword(client);
            registrationService.save(client);
            return "home";
        }


        model.addAttribute("passW", "");
        model.addAttribute("genderList", registrationService.getAllGender());
        model.addAttribute("languageList", registrationService.getLanguages());
        model.addAllAttributes(errorList);

         */
        return "edit_profil";

    }





}

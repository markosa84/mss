package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.model.user.Client;
import hu.ak_akademia.mss.model.user.Doctor;
import hu.ak_akademia.mss.model.user.MssUser;
import hu.ak_akademia.mss.service.HomeAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/")
public class HomeAdminController {


    @Autowired
    HomeAdminService adminService;

    HomeAdminController(HomeAdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping("/getAllClients")
    public String getAllClients(Model model) {

        List<Client> clients = adminService.getAllClients();
        boolean isEmpty = true;

        if (clients.size() != 0){
            isEmpty = false;
        }


        model.addAttribute("isEmpty", isEmpty);
        model.addAttribute("userList", clients);
        model.addAttribute("clientIsActive", true);

        return "home_admin";
    }

    @GetMapping("/getActiveClients")
    public String getActiveUserLlist(Model model) {

        List<Client> clients = adminService.getActiveClients();
        boolean isEmpty = true;

        if (clients.size() != 0){
            isEmpty = false;
        }


        model.addAttribute("isEmpty", isEmpty);
        model.addAttribute("userList", clients);
        model.addAttribute("activeClientIsActive", true);

        return "home_admin";
    }

    @GetMapping("/getInActiveClients")
    public String getInactiveUserLlist(Model model) {

        List<Client> clients = adminService.getInActiveClients();
        boolean isEmpty = true;

        if (clients.size() != 0){
            isEmpty = false;
        }


        model.addAttribute("isEmpty", isEmpty);
        model.addAttribute("userList", clients);
        model.addAttribute("inactiveClientIsActive", true);

        return "home_admin";
    }

    @GetMapping("/getDoctors")
    public String getDoctors(Model model) {

        List<Doctor> doctors = adminService.getDoctors();
        boolean isEmpty = true;

        if (doctors.size() != 0){
            isEmpty = false;
        }


        model.addAttribute("isEmpty", isEmpty);

        model.addAttribute("userList", doctors);
        model.addAttribute("doctorIsActive", true);

        return "home_admin";
    }

    @GetMapping("/filterClientByName")
    public String filterByName( String name, Model model) {

        List<Client> clients = Collections.emptyList();
        boolean isEmpty = true;



        model.addAttribute("filterByFullNameIsActive", true);


        if (name.contains(" ")) {
            String[] fullName = name.split(" ");
            String firstName = fullName[0];
            String lastName = name.substring(firstName.length() + 1);
            clients = (List<Client>) adminService.getClientByName(firstName, lastName);
            model.addAttribute("userList", clients);
        }
        if (clients.size() != 0){
            isEmpty = false;
        }
        model.addAttribute("isEmpty", isEmpty);

        return "home_admin";
    }

    @GetMapping("/filterClientByLastName")
    public String filterByLastName( String name, Model model) {

        List<Client> clients = (List<Client>) adminService.getClientByLastName(name);
        boolean isEmpty = true;

        if (clients.size() != 0){
            isEmpty = false;
        }
        model.addAttribute("isEmpty", isEmpty);
        model.addAttribute("userList", adminService.getClientByLastName(name));
        model.addAttribute("filterByLastNameIsActive", true);


        return "home_admin";
    }

    @PostMapping("/deleteById")
    public String deleteById(int id, Model model){

        String role = adminService.getById(id).getRoles();
        adminService.deleteById(id);


        if (role.equals("ROLE_CLIENT")){
            return "redirect:/getAllClients";
        } else if (role.equals("ROLE_DOCTOR")) {
            return "redirect:/getDoctors";
        }

        return "home_admin";
    }

    @PostMapping("/details")
    public String details(int id, Model model){
        var mssUser = adminService.getById(id);
        System.out.println(mssUser instanceof Client);



        String role = mssUser.getRoles();
        if (role.equals("ROLE_CLIENT")){
            return "redirect:/getAllClients";
        } else if (role.equals("ROLE_DOCTOR")) {
            return "redirect:/getDoctors";
        }

        return "home_admin";
    }


    @PostMapping("/modify")
    public String modify(int id, Model model){
        var mssUser = adminService.getById(id);




        String role = mssUser.getRoles();
        if (role.equals("ROLE_CLIENT")){
            return "redirect:/getAllClients";
        } else if (role.equals("ROLE_DOCTOR")) {
            return "redirect:/getDoctors";
        }

        return "home_admin";
    }

    @GetMapping("/getAll")
    public String getAll(){
        List<MssUser> users = adminService.getAll();
        users.stream().forEach(u -> System.out.println(u.getFirstName() + " " + u.getLastName()));
        return "működik";
    }


}

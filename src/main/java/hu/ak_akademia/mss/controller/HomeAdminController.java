package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.service.HomeAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeAdminController {

    @Autowired
    HomeAdminService adminService;

    HomeAdminController(HomeAdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping("/getAllClients")
    public String userLlist(Model model) {
        model.addAttribute("clientList", adminService.getAllClients());
        return "home_admin";
    }
}

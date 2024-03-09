package hu.ak_akademia.mss.controller;


import hu.ak_akademia.mss.dto.AreaOfExpertiseDTO;
import hu.ak_akademia.mss.service.AreaOfExpertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class AreaOfExpertiseController {

    @Autowired
    private AreaOfExpertiseService areaOfExpertiseService;

    @GetMapping("/components/departmentcard")
    public List<AreaOfExpertiseDTO> getDepartmentCard() {
        return areaOfExpertiseService.getAreOfExpertiseDTO();
    }

}

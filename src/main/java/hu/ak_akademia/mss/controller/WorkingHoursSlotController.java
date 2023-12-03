package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.model.Slot;
import hu.ak_akademia.mss.service.AreaOfExpertiseService;
import hu.ak_akademia.mss.service.DoctorsWorkingHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class WorkingHoursSlotController {

    @Autowired
    private AreaOfExpertiseService areaOfExpertiseService;
    @Autowired
    private DoctorsWorkingHoursService doctorsWorkingHoursService;

    @GetMapping("/slots/{areaOfExpertiseId}")
    public List<Slot> specialArea(@PathVariable int areaOfExpertiseId) {
        return areaOfExpertiseService.getSlots(areaOfExpertiseId);
    }
}

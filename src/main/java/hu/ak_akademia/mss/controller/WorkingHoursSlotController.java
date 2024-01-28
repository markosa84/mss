package hu.ak_akademia.mss.controller;

import hu.ak_akademia.mss.model.Slot;
import hu.ak_akademia.mss.service.AreaOfExpertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/")
public class WorkingHoursSlotController {

    @Autowired
    private AreaOfExpertiseService areaOfExpertiseService;

    @GetMapping("/slots/{areaOfExpertiseId}")
    public ResponseEntity<List<Slot>> specialArea(@PathVariable int areaOfExpertiseId) {
        try {
            return areaOfExpertiseService.getSlots(areaOfExpertiseId);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(400).body(List.of());
        }
    }
}

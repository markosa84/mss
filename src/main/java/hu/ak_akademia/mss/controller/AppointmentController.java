package hu.ak_akademia.mss.controller;


import hu.ak_akademia.mss.dto.AppointmentDto;
import hu.ak_akademia.mss.model.Slot;
import hu.ak_akademia.mss.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

@RestController
@ConfigurationProperties(prefix = "slot")
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private Integer time;


    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @PostMapping("/save")
    public ResponseEntity saveDate(@RequestBody Map<String, Object> payLoad) {
        try {
            LocalTime startTime = LocalTime.parse((String) payLoad.get("startTime"), timeFormatter);
            LocalTime endTime = LocalTime.parse((String) payLoad.get("endTime"), timeFormatter);
            Slot slot = new Slot((int) payLoad.get("slotId"), startTime, endTime);
            return appointmentService.saveAppointment((Integer) payLoad.get("drID"), (String) payLoad.get("username"), slot, (String) payLoad.get("areaOfExpertise"), LocalDate.parse((String) payLoad.get("date"), dateFormatter));
        } catch (NullPointerException e) {
            return new ResponseEntity<>("None of the parameter values can be null!", HttpStatus.valueOf(400));
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>("You entered the wrong date format!! Try the Date in this format: yyyy-MM-dd, and the times in this format: HH:mm:ss!!", HttpStatus.valueOf(400));
        } catch (ClassCastException e) {
            return new ResponseEntity<>("drId parameter must be an integer", HttpStatus.valueOf(400));
        }
    }

    @PostMapping("/getAppointments")
    public ResponseEntity<List<AppointmentDto>> getAppointments(@RequestParam(name = "specialtyId") int specialtyId,
                                                                @RequestParam(name = "doctorId") List<Integer> doctorIds) {
        // Adatbázisból történő lekérdezés a specialtyId és doctorIds alapján
        List<AppointmentDto> appointments = appointmentService.getAppointmentsBySpecialtyAndDoctors(specialtyId, doctorIds);
        // Visszaadhatod a megfelelő választ a kliensnek
        return ResponseEntity.ok(appointments);
    }
}
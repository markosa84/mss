package hu.ak_akademia.mss.controller;


import hu.ak_akademia.mss.dto.AppointmentDetailsDTO;
import hu.ak_akademia.mss.dto.AppointmentDto;
import hu.ak_akademia.mss.model.Slot;
import hu.ak_akademia.mss.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
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
    public ResponseEntity<AppointmentDetailsDTO> saveDate(@RequestBody Map<String, Object> payLoad, Authentication authentication) {
        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            LocalTime startTime = LocalTime.parse((String) payLoad.get("startTime"), timeFormatter);
            LocalTime endTime = LocalTime.parse((String) payLoad.get("endTime"), timeFormatter);
            Slot slot = new Slot((int) payLoad.get("slotId"), startTime, endTime);
            return appointmentService.saveAppointment((Integer) payLoad.get("drID"), (String) payLoad.get("username"), slot, (String) payLoad.get("areaOfExpertise"), LocalDate.parse((String) payLoad.get("date"), dateFormatter));
        } catch (NullPointerException e) {
            httpHeaders.add("info", "None of the parameter values can be null!");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(400));
        } catch (DateTimeParseException e) {
            httpHeaders.add("info", "You entered the wrong date format!! Try the Date in this format: yyyy-MM-dd, and the times in this format: HH:mm:ss!!");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(400));
        } catch (ClassCastException e) {
            httpHeaders.add("info", "drId parameter must be an integer");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(400));
        }
    }

    @PreAuthorize("hasAuthority('ROLE_CLIENT') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_ASSISTANT')")
    @GetMapping("/get/client")
    public ResponseEntity<List<AppointmentDetailsDTO>> getAppointmentsByClient(@RequestParam int clientId, Authentication authentication) {
        return appointmentService.getAppointmentByClient(clientId);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/get/doctors")
    public ResponseEntity<List<AppointmentDetailsDTO>> getAppointmentsByDoctors(@RequestParam List<Integer> doctorsIds, @RequestParam String startDate, @RequestParam String endDate) {

        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            LocalDate stDate = LocalDate.parse(startDate, dateFormatter);
            LocalDate enDate = LocalDate.parse(endDate, dateFormatter);
            return ResponseEntity.status(200).body(appointmentService.getAppointmentsByDoctors(doctorsIds, stDate, enDate));
        } catch (DateTimeParseException e) {
            httpHeaders.add("info", "You entered the wrong date format!! Try the Date in this format: yyyy-MM-dd, and the times in this format: HH:mm:ss!!");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(400));
        }
    }

    @PreAuthorize("hasAuthority('ROLE_DOCTOR') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/get/doctor")
    public ResponseEntity<List<AppointmentDetailsDTO>> getAppointmentsByDoctor(@RequestParam int doctorId, @RequestParam String startDate, @RequestParam String endDate, Authentication authentication) {
        HttpHeaders httpHeaders = new HttpHeaders();

        try {
            LocalDate stDate = LocalDate.parse(startDate, dateFormatter);
            LocalDate enDate = LocalDate.parse(endDate, dateFormatter);
            return appointmentService.getAppointmentsByDoctor(doctorId, stDate, enDate);
        } catch (DateTimeParseException e) {
            httpHeaders.add("info", "You entered the wrong date format!! Try the Date in this format: yyyy-MM-dd, and the times in this format: HH:mm:ss!!");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(400));
        }
    }

    @PreAuthorize("hasAuthority('ROLE_CLIENT') or hasAuthority('ROLE_DOCTOR') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/delete/doctor")
    public ResponseEntity<String> deleteByIdWithDoctor(@RequestParam int id, Authentication authentication) {
        String userEmail = authentication.getName();
        HttpHeaders httpHeaders = new HttpHeaders();

        ResponseEntity response = appointmentService.deleteAppointmentByIdWithDoctor(id, userEmail);

        httpHeaders.add("info", (String) response.getBody());
        return ResponseEntity.status(response.getStatusCode()).headers(httpHeaders).body(null);
    }

    @PreAuthorize("hasAuthority('ROLE_CLIENT') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/delete/byClient")
    public ResponseEntity<String> deleteByIdWithClient(@RequestParam int id, Authentication authentication) {
        HttpHeaders httpHeaders = new HttpHeaders();
        String userEmail = authentication.getName();

        ResponseEntity response = appointmentService.deleteAppointmentByIdWithClient(id, userEmail);

        httpHeaders.add("info", (String) response.getBody());
        return ResponseEntity.status(response.getStatusCode()).headers(httpHeaders).body(null);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/delete/admin")
    public ResponseEntity<List<AppointmentDetailsDTO>> deleteByIdWithAdmin(@RequestParam int id) {
        HttpHeaders httpHeaders = new HttpHeaders();

        ResponseEntity response = appointmentService.deleteAppointmentById(id);
        httpHeaders.add("info", (String) response.getBody());
        return ResponseEntity.status(response.getStatusCode()).headers(httpHeaders).body(null);
    }


    //csak a szakirány.Id je jön paraméterként, javitva Balász kérésére
    @GetMapping("/getAppointments")
    public ResponseEntity<List<AppointmentDto>> getAppointments(@RequestParam(name = "specialtyId") int specialtyId) {
        // Adatbázisból történő lekérdezés a specialtyId és
        List<AppointmentDto> appointments = appointmentService.getAppointmentsBySpecialtyAndDoctors(specialtyId);

        // Visszaadhatod a megfelelő választ a kliensnek
        return ResponseEntity.ok(appointments);
    }
}

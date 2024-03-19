package hu.ak_akademia.mss.controller;


import hu.ak_akademia.mss.dto.AppointmentDetailsDTO;
import hu.ak_akademia.mss.dto.AppointmentDto;
import hu.ak_akademia.mss.model.Slot;
import hu.ak_akademia.mss.service.AppointmentService;
import hu.ak_akademia.mss.service.DoctorService;
import hu.ak_akademia.mss.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@RestController
@ConfigurationProperties(prefix = "slot")
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private final AppointmentService appointmentService;
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final EmailService emailService;
    private final DoctorService doctorService;
    private Integer time;

    public AppointmentController(AppointmentService appointmentService, EmailService emailService, DoctorService doctorService) {
        this.appointmentService = appointmentService;
        this.emailService = emailService;
        this.doctorService = doctorService;
    }


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
            ResponseEntity<AppointmentDetailsDTO> saveResult = appointmentService.saveAppointment((Integer) payLoad.get("drID"), (String) payLoad.get("username"), slot, (String) payLoad.get("areaOfExpertise"), LocalDate.parse((String) payLoad.get("date"), dateFormatter));
            if (saveResult.getStatusCode().is2xxSuccessful()) {
                String userEmail = (String) payLoad.get("username");
                String time1 = (String) payLoad.get("startTime");
                String time2 = (String) payLoad.get("endTime");
                int doctorId = (int) payLoad.get("drID");
                String doctorName = doctorService.getDoctorName(doctorId);
                String areaOfExpertise = (String) payLoad.get("areaOfExpertise");
                String emailContent = "Kedves " + userEmail + "! Köszönjük a foglalást. A foglalás a következő időpontban lett rögzítve: " + time1 + " - " + time2 + ". Szakirány: " + areaOfExpertise + " " + " Orvosnál :" + doctorName;
                emailService.sendAppointmentConfirmationEmail(userEmail, emailContent);
            }
            return saveResult;
        } catch (NullPointerException e) {
            httpHeaders.add("info", "None of the parameter values can be null!");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(400));
        } catch (DateTimeParseException e) {
            httpHeaders.add("info", "You entered the wrong date format!! Try the Date in this format: yyyy-MM-dd, and the times in this format: HH:mm:ss!!");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(400));
        } catch (ClassCastException e) {
            httpHeaders.add("info", "drId parameter must be an integer");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(400));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_CLIENT') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_ASSISTANT')")
    @GetMapping("/get/client/{clientId}")
    public ResponseEntity<List<AppointmentDetailsDTO>> getAppointmentsByClient(@PathVariable int clientId) {
        return appointmentService.getAppointmentByClient(clientId);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_DOCTOR')")
    @GetMapping("/get/doctors")
    public ResponseEntity<List<AppointmentDetailsDTO>> getAppointmentsByDoctors(@RequestParam List<Integer> doctorsIds, @RequestParam String startDate, @RequestParam String endDate) {
        try {
            return ResponseEntity.status(200).body(appointmentService.getAppointmentsByDoctors(doctorsIds, LocalDate.parse(startDate), LocalDate.parse(endDate)));
        } catch (DateTimeParseException e) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("info", "You entered the wrong date format!! Try the Date in this format: yyyy-MM-dd, and the times in this format: HH:mm:ss!!");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(400));
        }
    }

    @PreAuthorize("hasAuthority('ROLE_DOCTOR') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/get/doctor")
    public ResponseEntity<List<AppointmentDetailsDTO>> getAppointmentsByDoctor(@RequestParam int doctorId, @RequestParam String startDate, @RequestParam String endDate, Authentication authentication) {
        try {
            LocalDate stDate = LocalDate.parse(startDate, dateFormatter);
            LocalDate enDate = LocalDate.parse(endDate, dateFormatter);
            return appointmentService.getAppointmentsByDoctor(doctorId, stDate, enDate);
        } catch (DateTimeParseException e) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("info", "You entered the wrong date format!! Try the Date in this format: yyyy-MM-dd, and the times in this format: HH:mm:ss!!");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(400));
        }
    }

    private ResponseEntity<String> performAction(String operationName, List<String> requiredAuthorities, int id, Authentication authentication, Supplier<ResponseEntity<String>> action) {
        if (requiredAuthorities.stream().anyMatch(authority -> authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals(authority)))) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("info", action.get().getBody());
            return ResponseEntity.ok("Appointment deleted successfully!"); // Itt törzs nélküli választ adunk vissza
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @DeleteMapping("/delete/doctor")
    public ResponseEntity<String> deleteAppointmentByIdWithDoctor(@RequestParam int id, Authentication authentication) {
        return performAction("delete by doctor", Arrays.asList("ROLE_CLIENT", "ROLE_DOCTOR", "ROLE_ADMIN"), id, authentication, () -> appointmentService.deleteAppointmentByIdWithDoctor(id, authentication.getName()));
    }

    @DeleteMapping("/delete/byClient")
    public ResponseEntity<String> deleteAppointmentByIdWithClient(@RequestParam int id, Authentication authentication) {
        return performAction("delete by client", Arrays.asList("ROLE_CLIENT", "ROLE_ADMIN"), id, authentication, () -> appointmentService.deleteAppointmentByIdWithClient(id, authentication.getName()));
    }

    @DeleteMapping("/delete/admin")
    public ResponseEntity<String> deleteAppointmentByIdWithAdmin(@RequestParam int id) {
        return performAction("delete by admin", Collections.singletonList("ROLE_ADMIN"), id, null, () -> appointmentService.deleteAppointmentById(id));
    }

    @GetMapping("/getAppointments")
    public ResponseEntity<List<AppointmentDto>> getAppointments(@RequestParam(name = "specialtyId") int specialtyId) {
        List<AppointmentDto> appointments = appointmentService.getAppointmentsBySpecialtyAndDoctors(specialtyId);
        return ResponseEntity.ok(appointments);
    }
}

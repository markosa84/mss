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

    @PreAuthorize("#authentication.name == #payload['clientEmail'] or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_ASSISTANT')")
    @GetMapping("/get/client")
    public ResponseEntity<List<AppointmentDetailsDTO>> getAppointmentsByClient(@RequestBody  Map<String, String> payload, Authentication authentication){
        HttpHeaders httpHeaders = new HttpHeaders();
        if (!payload.containsKey("clientId")){
            httpHeaders.add("info", "client email param can not be a null, ot empty string!");
            return ResponseEntity.status(400).headers(httpHeaders).body(null);
        }

        int clientId = Integer.parseInt(payload.get("clientId"));
        return appointmentService.getAppointmentByClient(clientId);
        }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/get/doctors")
    public ResponseEntity<List<AppointmentDetailsDTO>> getAppointmentsByDoctors(@RequestBody List<Integer> doctorsIds, @RequestBody Map<String,String> payload) {
        if (!payload.containsKey("startDate") || !payload.containsKey("endDate")){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("info", "startDate or endDate Param is missing!");
            return ResponseEntity.status(400).headers(httpHeaders).body(null);
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            LocalDate startDate = LocalDate.parse(payload.get("startDate"), dateFormatter);
            LocalDate endDate = LocalDate.parse(payload.get("endDate"), dateFormatter);

            return ResponseEntity.status(200).body(appointmentService.getAppointmentsByDoctors(doctorsIds, startDate, endDate));

            //List<AppointmentDetailsDTO> appointmentDTO = appointmentService.getAppointmentsByDoctors(doctorsIds);
            //return ResponseEntity.status(200).body(appointmentDTO);
        } catch (DateTimeParseException e) {
            httpHeaders.add("info", "You entered the wrong date format!! Try the Date in this format: yyyy-MM-dd, and the times in this format: HH:mm:ss!!");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(400));
        }
    }

    @PreAuthorize("#authentication.name == #payload['doctorEmail'] or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/get/doctor")
    public ResponseEntity<List<AppointmentDetailsDTO>> getAppointmentsByDoctor(@RequestBody Map<String,String> payload, Authentication authentication){
        HttpHeaders httpHeaders = new HttpHeaders();

        if (!payload.containsKey("doctorId")){
            httpHeaders.add("info", "client email param can not be a null, ot empty string!");
            return ResponseEntity.status(400).headers(httpHeaders).body(null);
        }

        try {
            LocalDate startDate = LocalDate.parse(payload.get("startDate"), dateFormatter);
            LocalDate endDate = LocalDate.parse(payload.get("endDate"), dateFormatter);
            int doctorId = Integer.parseInt(payload.get("doctorId"));
            return appointmentService.getAppointmentsByDoctor(doctorId, startDate, endDate);
        }  catch (DateTimeParseException e) {
            httpHeaders.add("info", "You entered the wrong date format!! Try the Date in this format: yyyy-MM-dd, and the times in this format: HH:mm:ss!!");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(400));
        }
    }

    @PreAuthorize("hasAuthority('ROLE_CLIENT') or hasAuthority('ROLE_DOCTOR') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/delete/doctor")
    public ResponseEntity<List<AppointmentDetailsDTO>> deleteByIdWithDoctor(@RequestParam int id, Authentication authentication){
        String userEmail = authentication.getName();
        HttpHeaders httpHeaders = new HttpHeaders();
        Map<String, String> payload = new HashMap<>();

            ResponseEntity response = appointmentService.deleteAppointmentByIdWithDoctor(id, userEmail);
            if (response.getStatusCode().value() != 200){
                httpHeaders.add("info", (String) response.getBody());
                return ResponseEntity.status(response.getStatusCode()).headers(httpHeaders).body(null);
            } else {
                payload.put("doctorEmail", userEmail);
                return getAppointmentsByDoctor(payload ,authentication);
            }
    }

    @PreAuthorize("hasAuthority('ROLE_CLIENT') or hasAuthority('ROLE_ADMIN')")
    @GetMapping("/delete/byClient")
    public ResponseEntity<List<AppointmentDetailsDTO>> deleteByIdWithClient(@RequestParam int id, Authentication authentication) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Map<String, String> payload = new HashMap<>();
        String userEmail = authentication.getName();

        ResponseEntity response = appointmentService.deleteAppointmentByIdWithClient(id, userEmail);

        if (response.getStatusCode().value() != 200) {
            httpHeaders.add("info", (String) response.getBody());
            return ResponseEntity.status(response.getStatusCode()).headers(httpHeaders).body(null);
        }else {
            payload.put("clientEmail", userEmail);
            return getAppointmentsByClient(payload ,authentication);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/delete/admin")
    public ResponseEntity<List<AppointmentDetailsDTO>> deleteByIdWithAdmin(@RequestParam int id){
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

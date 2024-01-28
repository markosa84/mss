package hu.ak_akademia.mss.service;


import hu.ak_akademia.mss.dto.AppointmentDetailsDTO;
import hu.ak_akademia.mss.dto.AppointmentDto;
import hu.ak_akademia.mss.dto.DoctorTimeSlotDto;
import hu.ak_akademia.mss.model.*;
import hu.ak_akademia.mss.model.user.Client;
import hu.ak_akademia.mss.model.user.Doctor;
import hu.ak_akademia.mss.model.user.MssUser;
import hu.ak_akademia.mss.repository.AppointmentRepository;
import hu.ak_akademia.mss.repository.AppointmentStatusRepository;
import hu.ak_akademia.mss.repository.AreaOfExpertiseRepository;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    private Map<Integer, List<String>> intervalMap;

    @Value("${appointment.endDateOffset}")
    private int endDateOffset;


    public static class TimeRange {
        private LocalTime startTime;
        private LocalTime endTime;

        public TimeRange(LocalTime startTime, LocalTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public LocalTime getStartTime() {
            return startTime;
        }

        public LocalTime getEndTime() {
            return endTime;
        }


    }

    private final DoctorsWorkingHoursService doctorsWorkingHoursService;

    private final AppointmentRepository appointmentRepository;

    private final MSSUserRepository mssUserRepository;

    private final AreaOfExpertiseRepository areaOfExpertiseRepository;

    private final AppointmentStatusRepository appointmentStatusRepository;

    private final DoctorService doctorService;

    private AreaOfExpertise getAreaOfExpertise(int specialtyId) {

        Optional<AreaOfExpertise> byId = areaOfExpertiseRepository.findById(specialtyId);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            return null;
        }
    }

    private AreaOfExpertise areaOfExpertise;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository,
                              MSSUserRepository mssUserRepository,
                              AreaOfExpertiseRepository areaOfExpertiseRepository,
                              AppointmentStatusRepository appointmentStatusRepository,
                              DoctorsWorkingHoursService doctorsWorkingHoursService,
                              DoctorService doctorService
    ) {
        this.appointmentRepository = appointmentRepository;
        this.mssUserRepository = mssUserRepository;
        this.areaOfExpertiseRepository = areaOfExpertiseRepository;
        this.appointmentStatusRepository = appointmentStatusRepository;
        this.doctorsWorkingHoursService = doctorsWorkingHoursService;
        this.doctorService = doctorService;
    }


    public ResponseEntity<AppointmentDetailsDTO> saveAppointment(int drId, String username, Slot slot, String name, LocalDate date) {
        ResponseEntity<Appointment> response = createAppointment(drId, username, slot, name, date);
        if (response.getStatusCode() != HttpStatus.valueOf(200)) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("info", response.getHeaders().get("info").get(0));
            return ResponseEntity.status(response.getStatusCode().value()).headers(httpHeaders).body(null);
        }

        Appointment appointment = response.getBody();
        List<Appointment> existingAppointmentInTheSameTimeByDoctor = appointmentRepository.getAppointmentsByDateAndDoctor(appointment.getStartDate(), appointment.getEndDate(), appointment.getMssUserDoctor().getUserId());
        if (!existingAppointmentInTheSameTimeByDoctor.isEmpty()) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("info", "Sorry!! This appointment is already booked");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(400));
        }
        List<Appointment> existingAppointmentInTheSameTimeByClient = appointmentRepository.getAppointmentsByDateAndClient(appointment.getStartDate(), appointment.getEndDate(), appointment.getMssUserClient().getUserId());
        if (!existingAppointmentInTheSameTimeByClient.isEmpty()) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("info", "Sorry!! You already have another booked appointment at this time");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(400));
        }
        Appointment result = appointmentRepository.save(appointment);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("info", "The appointment was successfully booked");
        AppointmentDetailsDTO responseBody = new AppointmentDetailsDTO(result.getId(), result.getMssUserDoctor().getUserId(), result.getMssUserClient().getEmail(), result.getStartDate().toString(), result.getEndDate().toString(), result.getAreaOfExpertise().getName());
        return new ResponseEntity<>(responseBody, httpHeaders, HttpStatus.valueOf(200));
    }

    public Optional<Appointment> getAppointmentById(int id) {
        return appointmentRepository.findById(id);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public ResponseEntity<String> deleteAppointmentByIdWithDoctor(int id, String userEmail) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isEmpty()) {
            return ResponseEntity.status(404).body("Appointment was not found!");
        } else {
            if (optionalAppointment.get().getMssUserDoctor().getEmail().equals(userEmail)) {
                appointmentRepository.deleteById(id);
                return ResponseEntity.status(200).body("Delete was successful");
            }
            return ResponseEntity.status(403).body("You don't have role to delete!");
        }
    }

    public ResponseEntity<String> deleteAppointmentByIdWithClient(int id, String userEmail) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        LocalDate date = optionalAppointment.get().getStartDate().toLocalDate();
        if (optionalAppointment.isEmpty()) {
            return ResponseEntity.status(404).body("Appointment was not found!");
        } else {
            if (optionalAppointment.get().getMssUserClient().getEmail().equals(userEmail)) {
                if (LocalDate.now().until(date, ChronoUnit.DAYS) > 1) {
                    appointmentRepository.deleteById(id);
                    return ResponseEntity.status(200).body("Delete was successful");
                } else {
                    return ResponseEntity.status(403).body("You cannot cancel the appointment the day before!");
                }
            }
            return ResponseEntity.status(403).body("You don't have role to delete!");
        }
    }

    public ResponseEntity<String> deleteAppointmentById(int id) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isEmpty()) {
            return ResponseEntity.status(404).body("Appointment was not found!");
        } else {
            appointmentRepository.deleteById(id);
            return ResponseEntity.status(200).body("Delete was successful");
        }
    }

    public List<Appointment> getAppointmentsByDateAndArea(LocalDateTime startDate, LocalDateTime endDate, int areaOfExpertiseId) {
        List<Appointment> appointments = appointmentRepository.getAppointmentsByDateAndArea(startDate, endDate, areaOfExpertiseId);
        appointments.removeIf(appointment -> appointment.getEndDate().isEqual(endDate));
        return appointments;
    }

    public List<Appointment> getAppointmentsByDateAndDoctor(LocalDateTime startDate, LocalDateTime endDate, int doctorId) {
        List<Appointment> appointments = appointmentRepository.getAppointmentsByDateAndDoctor(startDate, endDate, doctorId);
        appointments.removeIf(appointment -> appointment.getEndDate().isEqual(endDate));
        return appointments;
    }

    public ResponseEntity<Appointment> createAppointment(int drId, String username, Slot slot, String name, LocalDate date) {
        Client client;
        Doctor doctor;
        AppointmentStatus appointmentStatus;
        AreaOfExpertise areaOfExpertise;
        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            Optional<? extends MssUser> optionalClient = mssUserRepository.findByEmail(username);
            if (optionalClient.isPresent()) {
                client = (Client) optionalClient.get();
            } else {
                httpHeaders.add("info", "Client was not found");
                return ResponseEntity.status(404).headers(httpHeaders).body(null);
                //return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(404));
            }
        } catch (ClassCastException e) {
            httpHeaders.add("info", "The user id: " + username + " doesn't belong to a client!");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(400));
        }

        try {
            Optional<? extends MssUser> optionalDoctor = mssUserRepository.findById(drId);
            if (optionalDoctor.isPresent()) {
                doctor = (Doctor) optionalDoctor.get();
            } else {
                httpHeaders.add("info", "Doctor was not found");
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(404));
            }
        } catch (ClassCastException e) {
            httpHeaders.add("info", "The doctor id: " + drId + " doesn't belong to a doctor!");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(400));
        }

        Optional<AppointmentStatus> optionalAppointmentStatus = appointmentStatusRepository.findById(3);
        if (optionalAppointmentStatus.isPresent()) {
            appointmentStatus = optionalAppointmentStatus.get();
        } else {
            httpHeaders.add("info", "Appointment status was not found");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(404));
        }

        Optional<AreaOfExpertise> optionalAreaOfExpertise = areaOfExpertiseRepository.getByName(name);
        if (optionalAreaOfExpertise.isPresent()) {
            areaOfExpertise = optionalAreaOfExpertise.get();
        } else {
            httpHeaders.add("info", "Area of expertise was not found");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(404));
        }

        LocalTime startTime = slot.startTime();
        LocalTime endTime = slot.endTime();

        LocalDateTime startDate = LocalDateTime.of(date, startTime);
        LocalDateTime endDate = LocalDateTime.of(date, endTime);

        Appointment appointment = new Appointment(client, doctor, appointmentStatus, areaOfExpertise, startDate, endDate);
        return new ResponseEntity<>(appointment, HttpStatus.valueOf(200));
    }

    public ResponseEntity<List<AppointmentDetailsDTO>> getAppointmentByClient(int clientId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            Optional<? extends MssUser> optionalClient = mssUserRepository.findById(clientId);
            if (optionalClient.isPresent()) {
                Client client = (Client) optionalClient.get();
                List<Appointment> appointments = appointmentRepository.getAppointmentsByClient(client.getUserId());
                return ResponseEntity.status(200).body(convertToAppointmentDTO(appointments));
            } else {
                httpHeaders.add("info", "Client was not found");
                return ResponseEntity.status(404).headers(httpHeaders).body(null);
                //return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(404));
            }
        } catch (ClassCastException e) {
            httpHeaders.add("info", "The user id: " + clientId + " doesn't belong to a client!");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(400));
        }
    }

    public List<AppointmentDetailsDTO> convertToAppointmentDTO(List<Appointment> appointments) {
        List<AppointmentDetailsDTO> result = appointments.stream().map(a -> new AppointmentDetailsDTO(a.getId(), a.getMssUserDoctor().getUserId(), a.getMssUserClient().getEmail(), a.getStartDate().toString(), a.getEndDate().toString(), a.getAreaOfExpertise().getName())).collect(Collectors.toList());
        return result;
    }

    public ResponseEntity<List<AppointmentDetailsDTO>> getAppointmentsByDoctor(int doctorId, LocalDate start, LocalDate end) {
        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            Optional<? extends MssUser> optionalDoctor = mssUserRepository.findById(doctorId);
            if (optionalDoctor.isPresent()) {
                Doctor doctor = (Doctor) optionalDoctor.get();
                LocalDateTime startDate = start.atStartOfDay();
                LocalDateTime endDate = end.atTime(23, 59, 59);
                List<Appointment> appointments = appointmentRepository.getAppointmentsByDoctor(doctor.getUserId(), startDate, endDate);
                return ResponseEntity.status(200).body(convertToAppointmentDTO(appointments));
            } else {
                httpHeaders.add("info", "Client was not found");
                return ResponseEntity.status(404).headers(httpHeaders).body(null);
            }
        } catch (ClassCastException e) {
            httpHeaders.add("info", "The user id: " + doctorId + " doesn't belong to a client!");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(400));
        }
    }


    public List<AppointmentDetailsDTO> getAppointmentsByDoctors(List<Integer> doctorsIds, LocalDate start, LocalDate end) {
        List<Integer> checkedIdList = doctorsIds.stream().map(id -> mssUserRepository.getMSSUserByUserId(id)).filter(user -> user.getRoles().equals("Doctor")).map(dr -> dr.getUserId()).collect(Collectors.toList());
        List<Appointment> appointments = new ArrayList<>();
        LocalDateTime startDate = start.atStartOfDay();
        LocalDateTime endDate = end.atTime(23, 59, 59);
        checkedIdList.stream().forEach(id -> appointments.addAll(appointmentRepository.getAppointmentsByDoctor(id, startDate, endDate)));
        return convertToAppointmentDTO(appointments);
    }

    // Csaba dolgai
    public List<AppointmentDto> getAppointmentsBySpecialtyAndDoctors(int specialtyId) {
        DoctorsSchedule scheduleService = new DoctorsSchedule();
        scheduleService.setWorkingHoursService(doctorsWorkingHoursService);
        System.out.println(specialtyId);
        scheduleService.setAreaOfExpertise(getAreaOfExpertise(specialtyId));
        List<Slot> generatedSlots = scheduleService.generateSlots();
        List<Integer> doctorIds = doctorService.getDoctorIdsByAreaOfExpertise(specialtyId);
        List<Appointment> appointmentsFromDatabase = appointmentRepository.getAppointmentsByDateAndAreaModefied(
                LocalDateTime.now(), LocalDateTime.now().plusDays(endDateOffset), specialtyId);
        Map<LocalDate, Map<Integer, List<TimeRange>>> appointmentsByDoctor = appointmentsFromDatabase.stream()
                .filter(appointment -> doctorIds.contains(appointment.getMssUserDoctor().getUserId()))
                .collect(Collectors.groupingBy(
                        appointment -> appointment.getStartDate().toLocalDate(),
                        Collectors.toMap(
                                appointment -> appointment.getMssUserDoctor().getUserId(),
                                appointment -> new ArrayList<>(Collections.singletonList(new TimeRange(
                                        appointment.getStartDate().toLocalTime(),
                                        appointment.getEndDate().toLocalTime()
                                ))),
                                (existing, replacement) -> {
                                    existing.addAll(replacement);
                                    return existing;
                                },
                                TreeMap::new // Egyedi időintervallumokért TreeMap-et használunk
                        )
                ));
        List<AppointmentDto> appointmentDtos = convertToAppointmentDtoList(generatedSlots, appointmentsByDoctor);
        return appointmentDtos;
    }

    private List<AppointmentDto> convertToAppointmentDtoList(List<Slot> generatedSlots, Map<LocalDate, Map<Integer, List<TimeRange>>> appointmentsByDoctor) {
        List<AppointmentDto> result = new ArrayList<>();
        for (Map.Entry<LocalDate, Map<Integer, List<TimeRange>>> entry : appointmentsByDoctor.entrySet()) {
            LocalDate date = entry.getKey();
            List<DoctorTimeSlotDto> doctorTimeSlots = new ArrayList<>();
            for (Map.Entry<Integer, List<TimeRange>> doctorEntry : entry.getValue().entrySet()) {
                int doctorId = doctorEntry.getKey();
                List<TimeRange> timeRanges = doctorEntry.getValue();
                List<Integer> unavailableSlotIds = new ArrayList<>();
                for (TimeRange timeRange : timeRanges) {
                    unavailableSlotIds.addAll(findSlotIds(generatedSlots, timeRange.getStartTime()));
                }
                DoctorTimeSlotDto doctorTimeSlotDto = new DoctorTimeSlotDto(doctorId, unavailableSlotIds);
                doctorTimeSlots.add(doctorTimeSlotDto);

                // Kiíratás az ellenőrzés céljából
                System.out.println("Date: " + date);
                System.out.println("Doctor ID: " + doctorTimeSlotDto.getDoctorId());
                System.out.println("Unavailable Slot IDs: " + doctorTimeSlotDto.getUnavailableSlotIds());
            }
            result.add(new AppointmentDto(date, doctorTimeSlots));
        }
        return result;
    }

    private List<Integer> findSlotIds(List<Slot> generatedSlots, LocalTime startTime) {
        List<Integer> slotIds = new ArrayList<>();
        for (Slot slot : generatedSlots) {
            if (slot.startTime().equals(startTime)) {
                slotIds.add(slot.slotId());
            }
        }
        // Kiíratás az ellenőrzés céljából
        System.out.println("Slot IDs for startTime " + startTime + ": " + slotIds);

        return slotIds;
    }
}
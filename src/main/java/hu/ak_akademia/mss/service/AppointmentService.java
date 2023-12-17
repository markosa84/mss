package hu.ak_akademia.mss.service;


import hu.ak_akademia.mss.dto.AppointmentDto;
import hu.ak_akademia.mss.dto.DoctorTimeSlotDto;
import hu.ak_akademia.mss.model.Appointment;
import hu.ak_akademia.mss.model.AppointmentStatus;
import hu.ak_akademia.mss.model.AreaOfExpertise;
import hu.ak_akademia.mss.model.Slot;
import hu.ak_akademia.mss.model.user.Client;
import hu.ak_akademia.mss.model.user.Doctor;
import hu.ak_akademia.mss.model.user.MssUser;
import hu.ak_akademia.mss.repository.AppointmentRepository;
import hu.ak_akademia.mss.repository.AppointmentStatusRepository;
import hu.ak_akademia.mss.repository.AreaOfExpertiseRepository;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
  //  @Value("${time.intervals}")
   // private String timeIntervals;

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
    @Autowired
    private DoctorsWorkingHoursService doctorsWorkingHoursService;
    @Autowired
    private  AppointmentRepository appointmentRepository;
    @Autowired
    private  MSSUserRepository mssUserRepository;
    @Autowired
    private  AreaOfExpertiseRepository areaOfExpertiseRepository;
   @Autowired
    private  AppointmentStatusRepository appointmentStatusRepository;

    private AreaOfExpertise getAreaOfExpertise(int specialtyId) {
    return null;
    }
 private  AreaOfExpertise areaOfExpertise;
    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository,
                              MSSUserRepository mssUserRepository,
                              AreaOfExpertiseRepository areaOfExpertiseRepository,
                              AppointmentStatusRepository appointmentStatusRepository) {
        this.appointmentRepository = appointmentRepository;
        this.mssUserRepository = mssUserRepository;
        this.areaOfExpertiseRepository = areaOfExpertiseRepository;
        this.appointmentStatusRepository = appointmentStatusRepository;
    }


    public ResponseEntity saveAppointment(int drId, String username, Slot slot, String name, LocalDate date) {
        ResponseEntity response = createAppointment(drId, username, slot, name, date);
        if (response.getStatusCode() != HttpStatus.valueOf(200)) {
            return response;
        }

        Appointment appointment = (Appointment) response.getBody();

        List<Appointment> existingAppointmentInTheSameTimeByDoctor = appointmentRepository.getAppointmentsByDateAndDoctor(appointment.getStartDate(), appointment.getEndDate(), appointment.getMssUserDoctor().getUserId());
        if (!existingAppointmentInTheSameTimeByDoctor.isEmpty()) {
            return new ResponseEntity<>("Sorry!! This appointment is already booked", HttpStatus.valueOf(400));
        }
        List<Appointment> existingAppointmentInTheSameTimeByClient = appointmentRepository.getAppointmentsByDateAndClient(appointment.getStartDate(), appointment.getEndDate(), appointment.getMssUserClient().getUserId());
        if (!existingAppointmentInTheSameTimeByClient.isEmpty()) {
            return new ResponseEntity<>("Sorry!! You already have another booked appointment at this time", HttpStatus.valueOf(400));
        }
        appointmentRepository.save(appointment);
        return new ResponseEntity<>("The appointment was successfully booked", HttpStatus.valueOf(200));
    }

    public Optional<Appointment> getAppointmentById(int id) {
        return appointmentRepository.findById(id);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public void deleteAppointmentById(int id) {
        appointmentRepository.deleteById(id);
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

    public ResponseEntity createAppointment(int drId, String username, Slot slot, String name, LocalDate date) {
        Client client;
        Doctor doctor;
        AppointmentStatus appointmentStatus;
        AreaOfExpertise areaOfExpertise;
        try {
            Optional<? extends MssUser> optionalClient = mssUserRepository.findByEmail(username);
            if (optionalClient.isPresent()) {
                client = (Client) optionalClient.get();
            } else {
                return new ResponseEntity<>("Client was not found", HttpStatus.valueOf(404));
            }
        } catch (ClassCastException e) {
            return new ResponseEntity<>("The user id: " + username + " doesn't belong to a client!", HttpStatus.valueOf(400));
        }

        try {
            Optional<? extends MssUser> optionalDoctor = mssUserRepository.findById(drId);
            if (optionalDoctor.isPresent()) {
                doctor = (Doctor) optionalDoctor.get();
            } else {
                return new ResponseEntity<>("Doctor was not found", HttpStatus.valueOf(404));
            }
        } catch (ClassCastException e) {
            return new ResponseEntity<>("The doctor id: " + drId + " doesn't belong to a doctor!", HttpStatus.valueOf(400));
        }

        Optional<AppointmentStatus> optionalAppointmentStatus = appointmentStatusRepository.findById(3);
        if (optionalAppointmentStatus.isPresent()) {
            appointmentStatus = optionalAppointmentStatus.get();
        } else {
            return new ResponseEntity<>("Appointment status was not found", HttpStatus.valueOf(404));
        }

        Optional<AreaOfExpertise> optionalAreaOfExpertise = areaOfExpertiseRepository.getByName(name);
        if (optionalAreaOfExpertise.isPresent()) {
            areaOfExpertise = optionalAreaOfExpertise.get();
        } else {
            return new ResponseEntity<>("Area of expertise was not found", HttpStatus.valueOf(404));
        }

        LocalTime startTime = slot.startTime();
        LocalTime endTime = slot.endTime();

        LocalDateTime startDate = LocalDateTime.of(date, startTime);
        LocalDateTime endDate = LocalDateTime.of(date, endTime);

        Appointment appointment = new Appointment(client, doctor, appointmentStatus, areaOfExpertise, startDate, endDate);
        return new ResponseEntity<>(appointment, HttpStatus.valueOf(200));
    }
// Csaba dolgai
public List<AppointmentDto> getAppointmentsBySpecialtyAndDoctors(int specialtyId, List<Integer> doctorIds) {
    DoctorsSchedule scheduleService = new DoctorsSchedule();
    scheduleService.setWorkingHoursService(doctorsWorkingHoursService);
    scheduleService.setAreaOfExpertise(getAreaOfExpertise(specialtyId));
    List<Slot> generatedSlots = scheduleService.generateSlots();



    List<Appointment> appointmentsFromDatabase = appointmentRepository.getAppointmentsByDateAndAreaModefied(
            LocalDateTime.now(), LocalDateTime.now().plusDays(endDateOffset), specialtyId);

    Map<LocalDate, Map<Integer, TimeRange>> appointmentsByDoctor = appointmentsFromDatabase.stream()
            .filter(appointment -> doctorIds.contains(appointment.getMssUserDoctor().getUserId()))
            .collect(Collectors.groupingBy(
                    appointment -> appointment.getStartDate().toLocalDate(),
                    Collectors.toMap(
                            appointment -> appointment.getMssUserDoctor().getUserId(),
                            appointment -> new TimeRange(
                                    appointment.getStartDate().toLocalTime(),
                                    appointment.getEndDate().toLocalTime()
                            )
                    )
            ));
    List<AppointmentDto> appointmentDtos = convertToAppointmentDtoList(generatedSlots, appointmentsByDoctor);
    return appointmentDtos;
}

    private List<AppointmentDto> convertToAppointmentDtoList(List<Slot> generatedSlots, Map<LocalDate, Map<Integer, TimeRange>> appointmentsByDoctor) {
        List<AppointmentDto> result = new ArrayList<>();
        for (Map.Entry<LocalDate, Map<Integer, TimeRange>> entry : appointmentsByDoctor.entrySet()) {
            LocalDate date = entry.getKey();
            List<DoctorTimeSlotDto> doctorTimeSlots = new ArrayList<>();
            for (Map.Entry<Integer, TimeRange> doctorEntry : entry.getValue().entrySet()) {
                int doctorId = doctorEntry.getKey();
                TimeRange timeRange = doctorEntry.getValue();
                List<Integer> unavailableSlotIds = findSlotIds(generatedSlots, timeRange.getStartTime());
                DoctorTimeSlotDto doctorTimeSlotDto = new DoctorTimeSlotDto(doctorId, unavailableSlotIds);
                doctorTimeSlots.add(doctorTimeSlotDto);
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
            } else {
                slotIds.add(0);
            }
        }
        return slotIds;
    }

}
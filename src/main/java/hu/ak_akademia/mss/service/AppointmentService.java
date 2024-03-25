package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.dto.AppointmentDetailsDTO;
import hu.ak_akademia.mss.dto.AppointmentDto;
import hu.ak_akademia.mss.dto.RequestAppointmentDto;
import hu.ak_akademia.mss.dto.DoctorTimeSlotDto;
import hu.ak_akademia.mss.model.*;
import hu.ak_akademia.mss.model.user.Client;
import hu.ak_akademia.mss.model.user.Doctor;
import hu.ak_akademia.mss.model.user.MssUser;
import hu.ak_akademia.mss.repository.AppointmentRepository;
import hu.ak_akademia.mss.repository.AppointmentStatusRepository;
import hu.ak_akademia.mss.repository.AreaOfExpertiseRepository;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import hu.ak_akademia.mss.service.exceptions.AppointmentExistException;
import hu.ak_akademia.mss.service.exceptions.AppointmentStatusNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    private final DoctorsWorkingHoursService doctorsWorkingHoursService;

    private final AppointmentRepository appointmentRepository;

    private final MSSUserRepository mssUserRepository;

    private final AreaOfExpertiseRepository areaOfExpertiseRepository;

    private final AppointmentStatusRepository appointmentStatusRepository;

    private final DoctorService doctorService;

    private AreaOfExpertise getAreaOfExpertise(int specialtyId) {
        return areaOfExpertiseRepository.findById(specialtyId).orElseThrow();
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


    public Appointment saveAppointment(RequestAppointmentDto appointmentDto, Slot slot) {
        Appointment appointment = createAppointment(appointmentDto, slot);
        checkAppointmentIfExistAtDoctor(appointment);
        checkExistingAppointmentAtClient(appointment);
        return appointmentRepository.save(appointment);
    }

    private void checkAppointmentIfExistAtDoctor(Appointment appointment) {
        List<Appointment> doctorAppointment = appointmentRepository.getAppointmentsByDateAndDoctor(
                appointment.getStartDate(),
                appointment.getEndDate(),
                appointment.getMssUserDoctor().getUserId());
        if (!doctorAppointment.isEmpty()) {
            throw new AppointmentExistException("Sorry!! This appointment is already booked!");
        }
    }

    private void checkExistingAppointmentAtClient(Appointment appointment) {
        List<Appointment> existingAppointmentInTheSameTimeByClient = appointmentRepository.getAppointmentsByDateAndClient(
                appointment.getStartDate(),
                appointment.getEndDate(),
                appointment.getMssUserClient().getUserId());
        if (!existingAppointmentInTheSameTimeByClient.isEmpty()) {
            throw new AppointmentExistException("Sorry!! You already have another booked appointment at this time");
        }
    }

    public ResponseEntity<String> deleteAppointmentByIdWithDoctor(int id, String userEmail) {
        try {
            Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
            if (optionalAppointment.isEmpty()) {
                throw new NoSuchElementException("Appointment not found with id: " + id);
            }
            Appointment appointment = optionalAppointment.get();
            if (!appointment.getMssUserDoctor().getEmail().equals(userEmail)) {
                throw new IllegalArgumentException("You don't have the role to delete this appointment!");
            }
            appointmentRepository.deleteById(id);
            return ResponseEntity.status(200).body("Delete was successful");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body("Appointment was not found!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(403).body("You don't have role to delete!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Server error !!!!!.");
        }
    }


    public ResponseEntity<String> deleteAppointmentByIdWithClient(int id, String userEmail) {
        try {
            Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
            if (optionalAppointment.isEmpty()) {
                throw new NoSuchElementException("Appointment not found with id: " + id);
            }

            Appointment appointment = optionalAppointment.get();
            if (!appointment.getMssUserClient().getEmail().equals(userEmail)) {
                throw new IllegalArgumentException("You don't have the role to delete this appointment!");
            }

            LocalDate date = appointment.getStartDate().toLocalDate();
            if (LocalDate.now().until(date, ChronoUnit.DAYS) <= 1) {
                throw new IllegalStateException("You cannot cancel the appointment the day before!");
            }

            appointmentRepository.deleteById(id);
            return ResponseEntity.status(200).body("Delete was successful");
        } catch (Exception e) {
            throw e;
        }
    }


    public ResponseEntity<String> deleteAppointmentById(int id) {
        try {
            Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
            Appointment appointment = optionalAppointment.orElseThrow(() -> new NoSuchElementException("Appointment not found with id: " + id));
            appointmentRepository.deleteById(id);
            return ResponseEntity.status(200).body("Delete was successful");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body("Appointment was not found with id: " + id);
        }
    }


    public List<Appointment> getAppointmentsByDateAndArea(LocalDateTime startDate, LocalDateTime endDate, int areaOfExpertiseId) {
        Optional<List<Appointment>> appointmentsOptional = appointmentRepository.getAppointmentsByDateAndArea(startDate, endDate, areaOfExpertiseId);
        List<Appointment> appointments = appointmentsOptional.orElseThrow(() -> new NoSuchElementException("No appointments found for the given criteria"));
        appointments.removeIf(appointment -> appointment.getEndDate().isEqual(endDate));
        return appointments;
    }


    public List<Appointment> getAppointmentsByDateAndDoctor(LocalDateTime startDate, LocalDateTime endDate, int doctorId) {
        List<Appointment> appointments = appointmentRepository.getAppointmentsByDateAndDoctor(startDate, endDate, doctorId);
        appointments.removeIf(appointment -> appointment.getEndDate().isEqual(endDate));
        return appointments;
    }


    public Appointment createAppointment(RequestAppointmentDto appointmentDto, Slot slot) {
        try {
            Client client = (Client) mssUserRepository.findByEmail(appointmentDto.username()).orElseThrow(() -> new UsernameNotFoundException("Client was not found"));
            Doctor doctor = (Doctor) mssUserRepository.findById(appointmentDto.drId()).orElseThrow(() -> new UsernameNotFoundException("Doctor was not found"));
            AppointmentStatus appointmentStatus = appointmentStatusRepository.findById(AppointmentStatuses.BOOKED.getStatus()).orElseThrow(() -> new AppointmentStatusNotFoundException("Appointment status was not found"));
            AreaOfExpertise areaOfExpertise = areaOfExpertiseRepository.getByName(appointmentDto.areaOfExpertise()).orElseThrow(() -> new EntityNotFoundException("Area of expertise was not found"));
            LocalDateTime startDate = LocalDateTime.of(appointmentDto.date(), slot.startTime());
            LocalDateTime endDate = LocalDateTime.of(appointmentDto.date(), slot.endTime());
            return new Appointment(client, doctor, appointmentStatus, areaOfExpertise, startDate, endDate);
        } catch (ClassCastException e) {
            throw new UsernameNotFoundException("Doesn't belong to a client!: %s".formatted(e.getMessage()));
        }
    }

    public ResponseEntity<List<AppointmentDetailsDTO>> getAppointmentByClient(int clientId) {
        try {
            var client = (Client) mssUserRepository.findById(clientId).orElseThrow();
            List<Appointment> appointments = appointmentRepository.getAppointmentsByClient(client.getUserId());
            return ResponseEntity.status(200).body(convertToAppointmentDTO(appointments));
        } catch (ClassCastException e) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Warning-info", "The user id: " + clientId + " doesn't belong to a client!");
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.valueOf(400));
        }
    }

    private List<AppointmentDetailsDTO> convertToAppointmentDTO(List<Appointment> appointments) {
        return appointments.stream().map(a -> new AppointmentDetailsDTO(
                        a.getId(),
                        a.getMssUserDoctor().getUserId(),
                        a.getMssUserClient().getEmail(),
                        a.getStartDate().toString(),
                        a.getEndDate().toString(),
                        a.getAreaOfExpertise().getName()))
                .collect(Collectors.toList());
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
        List<Integer> checkedIdList = doctorsIds.stream().map(mssUserRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(MssUser::getUserId)
                .toList();
        List<Appointment> appointments = new ArrayList<>();
        checkedIdList.forEach(id -> appointments.addAll(appointmentRepository.getAppointmentsByDoctor(id, start.atStartOfDay(), end.atTime(23, 59, 59))));
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
                    unavailableSlotIds.addAll(findSlotIds(generatedSlots, timeRange.startTime()));
                }
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
            }
        }
        return slotIds;
    }

    public AppointmentDetailsDTO mappingAppointmentToDto(Appointment appointment) {
        return new AppointmentDetailsDTO(appointment.getId(),
                appointment.getMssUserDoctor().getUserId(),
                appointment.getMssUserClient().getEmail(),
                appointment.getStartDate().toString(),
                appointment.getEndDate().toString(),
                appointment.getAreaOfExpertise().getName());
    }

    public record TimeRange(LocalTime startTime, LocalTime endTime) {
    }
}

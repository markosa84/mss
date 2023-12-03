package hu.ak_akademia.mss.service;


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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    private final MSSUserRepository mssUserRepository;

    private final AreaOfExpertiseRepository areaOfExpertiseRepository;

    private final AppointmentStatusRepository appointmentStatusRepository;

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



    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
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
        List<Appointment> appointments = appointmentRepository.getAppointmentsByDateAndArea(startDate, endDate,  areaOfExpertiseId);
        appointments.removeIf(appointment -> appointment.getEndDate().isEqual(endDate));
        return appointments;
    }

    public List<Appointment> getAppointmentsByDateAndDoctor(LocalDateTime startDate, LocalDateTime endDate, int doctorId) {
        List<Appointment> appointments = appointmentRepository.getAppointmentsByDateAndDoctor(startDate, endDate,  doctorId);
        appointments.removeIf(appointment -> appointment.getEndDate().isEqual(endDate));
        return appointments;
    }

    public ResponseEntity createAppointment(String drId, String userId, Slot slot, String qualification, LocalDate date) {
        Client client;
        Doctor doctor;
        AppointmentStatus appointmentStatus;
        AreaOfExpertise areaOfExpertise;

        Optional<? extends MssUser> optionalClient = mssUserRepository.findByEmail(userId);
        if (optionalClient.isPresent()) {
            client = (Client) optionalClient.get();
        } else {
            return new ResponseEntity<>("Client was not found", HttpStatus.valueOf(404));
        }

        Optional<? extends MssUser> optionalDoctor = mssUserRepository.findByEmail(drId);
        if (optionalDoctor.isPresent()) {
            doctor= (Doctor) optionalDoctor.get();
        } else {
            return new ResponseEntity<>("Doctor was not found", HttpStatus.valueOf(404));
        }

        Optional<AppointmentStatus> optionalAppointmentStatus = appointmentStatusRepository.findById(3);
        if (optionalAppointmentStatus.isPresent()){
            appointmentStatus = optionalAppointmentStatus.get();
        } else {
            return new ResponseEntity<>("Appointment status was not found", HttpStatus.valueOf(404));
        }

        Optional<AreaOfExpertise> optionalAreaOfExpertise  = areaOfExpertiseRepository.getByQualification(qualification);
        if (optionalAreaOfExpertise.isPresent()){
            areaOfExpertise = optionalAreaOfExpertise.get();
        } else {
            return new ResponseEntity<>("Area of expertise was not found", HttpStatus.valueOf(404));
        }

        LocalTime startTime = slot.getStartTime();
        LocalTime endTime = slot.getEndTime();

        LocalDateTime startDate = LocalDateTime.of(date, startTime);
        LocalDateTime endDate = LocalDateTime.of(date, endTime);

        Appointment appointment = new Appointment(client, doctor, appointmentStatus, areaOfExpertise, startDate, endDate);
        saveAppointment(appointment);
        return new ResponseEntity<>("The appointment has been successfully booked", HttpStatus.valueOf(200));
    }
}





package hu.ak_akademia.mss.service;


import hu.ak_akademia.mss.model.Appointment;
import hu.ak_akademia.mss.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }


    public Appointment createAppointment(Appointment appointment) {
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
}





package hu.ak_akademia.mss.repository;

import hu.ak_akademia.mss.model.Appointment;

import hu.ak_akademia.mss.model.user.MssUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    @Query("SELECT a FROM Appointment a WHERE a.startDate BETWEEN :start AND :end AND a.endDate BETWEEN :start AND :end AND a.areaOfExpertise.id = :areaId")
    List<Appointment> getAppointmentsByDateAndArea(@Param("start") LocalDateTime startDate, @Param("end") LocalDateTime endDate, @Param("areaId") int areaOfExpertiseId);


    @Query("SELECT a FROM Appointment a WHERE a.startDate BETWEEN :start AND :end AND a.endDate BETWEEN :start AND :end AND a.mssUserDoctor.id = :doctorId")
    List<Appointment> getAppointmentsByDateAndDoctor(@Param("start") LocalDateTime startDate, @Param("end") LocalDateTime endDate, @Param("doctorId") int doctorId);
}

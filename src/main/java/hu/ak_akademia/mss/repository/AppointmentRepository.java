package hu.ak_akademia.mss.repository;

import hu.ak_akademia.mss.model.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    @Query("SELECT a FROM Appointment a WHERE a.startDate BETWEEN :start AND :end AND a.endDate BETWEEN :start AND :end AND a.areaOfExpertise.id = :areaId")
    List<Appointment> getAppointmentsByDateAndArea(@Param("start") LocalDateTime startDate, @Param("end") LocalDateTime endDate, @Param("areaId") int areaOfExpertiseId);


    @Query("SELECT a FROM Appointment a WHERE a.startDate BETWEEN :start AND :end AND a.endDate BETWEEN :start AND :end AND a.mssUserDoctor.id = :doctorId")
    List<Appointment> getAppointmentsByDateAndDoctor(@Param("start") LocalDateTime startDate, @Param("end") LocalDateTime endDate, @Param("doctorId") int doctorId);


    @Query("SELECT a FROM Appointment a WHERE a.startDate BETWEEN :start AND :end AND a.endDate BETWEEN :start AND :end AND a.mssUserClient.id = :clientId")
    List<Appointment> getAppointmentsByDateAndClient(@Param("start") LocalDateTime startDate, @Param("end") LocalDateTime endDate, @Param("clientId") int clientId);

    @Query("SELECT a FROM Appointment a WHERE a.mssUserClient.id = :clientId")
    List<Appointment> getAppointmentsByClient(@Param("clientId") int clientId);

    @Query("SELECT a FROM Appointment a WHERE a.mssUserDoctor.id = :doctorId")
    List<Appointment> getAppointmentsByDoctor(@Param("doctorId") int doctorId);

    @Query("SELECT a FROM Appointment a WHERE a.mssUserDoctor.id IN :doctorIds")
    List<Appointment> getAppointmentsByDoctors(@Param("doctorIds") List<Integer> doctorIds);


    @Query("SELECT a FROM Appointment a JOIN FETCH a.mssUserDoctor d " +
            "WHERE a.startDate BETWEEN :start AND :end " +
            "AND a.endDate BETWEEN :start AND :end " +
            "AND a.areaOfExpertise.id = :areaId " +
            "AND a.status.id <> 5 " +
            "ORDER BY a.startDate, d.userId")
    List<Appointment> getAppointmentsByDateAndAreaModefied(@Param("start") LocalDateTime startDate,
                                                           @Param("end") LocalDateTime endDate,
                                                           @Param("areaId") int areaOfExpertiseId);



}

package hu.ak_akademia.mss.repository;

import hu.ak_akademia.mss.model.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentStatusRepository extends JpaRepository<AppointmentStatus, Integer> {
}

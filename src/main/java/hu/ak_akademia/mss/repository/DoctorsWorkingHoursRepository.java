package hu.ak_akademia.mss.repository;

import hu.ak_akademia.mss.model.DoctorsWorkingHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorsWorkingHoursRepository extends JpaRepository<DoctorsWorkingHours, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM doctors_working_hours WHERE area_id = ?1")
    List<DoctorsWorkingHours> getAllDoctorsWorkingHours(int areaId);

}

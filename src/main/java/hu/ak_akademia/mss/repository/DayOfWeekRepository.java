package hu.ak_akademia.mss.repository;

import hu.ak_akademia.mss.model.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayOfWeekRepository extends JpaRepository <DayOfWeek, Integer> {
}

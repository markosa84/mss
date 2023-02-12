package hu.ak_akademia.mss.repository;

import hu.ak_akademia.mss.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderTableRepository extends JpaRepository<Gender,Long> {
}

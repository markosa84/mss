package hu.ak_akademia.mss.repository;

import hu.ak_akademia.mss.model.MssUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MSSUserRepository extends JpaRepository<MssUsers, Integer> {
}

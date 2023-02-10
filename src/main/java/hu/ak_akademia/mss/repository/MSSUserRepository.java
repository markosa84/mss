package hu.ak_akademia.mss.repository;

import hu.ak_akademia.mss.model.user.MssUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MSSUserRepository extends JpaRepository<MssUser, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM mssdatabase where userTypeId = ?1")
    Optional<List<? extends MssUser>> getAllGivenUserType(String userTypeId);

}

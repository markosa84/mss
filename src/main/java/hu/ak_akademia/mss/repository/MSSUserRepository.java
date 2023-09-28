package hu.ak_akademia.mss.repository;

import hu.ak_akademia.mss.model.AreaOfExpertise;
import hu.ak_akademia.mss.model.user.Client;
import hu.ak_akademia.mss.model.user.MssUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MSSUserRepository extends JpaRepository<MssUser, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM mssdatabase WHERE userTypeId = ?1")
    Optional<List<? extends MssUser>> getAllGivenUserType(String userTypeId);

    @Query(nativeQuery = true, value = "SELECT * FROM mss_user WHERE email = ?1 AND password = ?2")
    Optional<? extends MssUser> getMSSUserByEmail(String email, String password);

    @Query(nativeQuery = true, value = "SELECT * FROM mss_user WHERE email = ?1")
    Optional<? extends MssUser> findByEmail(String email);

    @Query("SELECT u FROM MssUser u JOIN u.areaOfExpertise a WHERE a.id = :areaId")
    List<MssUser> findDoctorsByAreaOfExpertise(@Param("areaId") int areaId);

    @Query("SELECT u FROM MssUser u WHERE u.roles = 'ROLE_CLIENT'")
    Optional<List<Client>> getAllClients();
}

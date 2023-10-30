package hu.ak_akademia.mss.repository;

import hu.ak_akademia.mss.model.user.Client;
import hu.ak_akademia.mss.model.user.Doctor;
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

    @Query("SELECT u FROM MssUser u WHERE u.roles = 'ROLE_CLIENT' ORDER BY u.lastName, u.firstName")
    Optional<List<Client>> getAllClients();

    @Query("SELECT u FROM MssUser u WHERE u.roles = 'ROLE_CLIENT' AND u.active = true ORDER BY u.lastName, u.firstName")
    Optional<List<Client>> getActiveClients();

    @Query("SELECT u FROM MssUser u WHERE u.roles = 'ROLE_CLIENT' AND u.active = false ORDER BY u.lastName, u.firstName")
    Optional<List<Client>> getInActiveClients();

    @Query("SELECT u FROM MssUser u WHERE u.roles = 'ROLE_DOCTOR' ORDER BY u.lastName, u.firstName")
    Optional<List<Doctor>> getDoctors();

    @Query("SELECT u FROM MssUser u WHERE u.roles = 'ROLE_CLIENT' AND u.firstName = ?1 AND lastName = ?2 ORDER BY u.firstName")
    Optional<List<Client>> getClientByName(String firstName, String lastName);

    @Query("SELECT u FROM MssUser u WHERE u.roles = 'ROLE_CLIENT' AND lastName = ?1 ORDER BY u.firstName")
    Optional<List<Client>> getClientByLastName(String lastName);

    MssUser getMSSUserByUserId(int id);
}

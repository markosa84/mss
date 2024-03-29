package hu.ak_akademia.mss.repository;

import hu.ak_akademia.mss.model.RegistrationVerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationVerificationRepository extends JpaRepository<RegistrationVerificationCode, Integer> {
    Optional<RegistrationVerificationCode> findByVerificationCode(String verificationCode);
}


package hu.ak_akademia.mss.mss_user_validation_process;

import hu.ak_akademia.mss.dto.MssUserDto;
import hu.ak_akademia.mss.model.user.Doctor;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import hu.ak_akademia.mss.service.RegistrationService;
import hu.ak_akademia.mss.service.RegistrationVerificationService;
import hu.ak_akademia.mss.service.validators.CompositeDoctorValidator;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

public class DoctorValidationRegistrationProcess implements MssUserValidationRegistrationProcess<Doctor> {

    private final MSSUserRepository mssUserRepository;
    private final RegistrationService registrationService;
    private final RegistrationVerificationService registrationVerificationService;

    public DoctorValidationRegistrationProcess(MSSUserRepository mssUserRepository, RegistrationService registrationService, RegistrationVerificationService registrationVerificationService) {
        this.mssUserRepository = mssUserRepository;
        this.registrationService = registrationService;
        this.registrationVerificationService = registrationVerificationService;
    }


    @Override
    public ResponseEntity<Collection<String>> controlMssUserRegistrationProcess(MssUserDto mssUserDto) throws MessagingException {
        var doctor = mappingDtoToMssUser(mssUserDto);
        var doctorValidator = new CompositeDoctorValidator(mssUserRepository);
        doctorValidator.validate(doctor);
        if (doctorValidator.getValidatorErrorList().isEmpty()) {
            registrationService.encryptPassword(doctor);
            registrationService.save(doctor);
            registrationVerificationService.performRegistrationVerification(doctor);
            return ResponseEntity.ok().body(Collections.emptyList());
        }
        return ResponseEntity.status(403).body(doctorValidator.getValidatorErrorList().values());
    }

    @Override
    public Doctor mappingDtoToMssUser(MssUserDto mssUserDto) {
        var doctor = new Doctor();
        doctor.setEmail(mssUserDto.getEmail());
        doctor.setPassword(mssUserDto.getPassword());
        doctor.setActive(false);
        doctor.setRegistrationDate(LocalDateTime.now());
        doctor.setFirstName(mssUserDto.getFirstName());
        doctor.setLastName(mssUserDto.getLastName());
        doctor.setLanguages(registrationService.getLanguages(mssUserDto.getLanguageId()));
        doctor.setAreaOfExpertise(registrationService.getAreaOfExpertises(mssUserDto.getAreaOfExpertiseId()));
        doctor.setGender(mssUserDto.getGenderId());
        doctor.setRoles("ROLE_DOCTOR");
        doctor.setPhoneNumber(mssUserDto.getPhoneNumber());
        return doctor;
    }
}

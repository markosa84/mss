package hu.ak_akademia.mss.mss_user_validation_process;

import hu.ak_akademia.mss.dto.MssUserDto;
import hu.ak_akademia.mss.model.user.MssUser;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.util.Collection;

public interface MssUserValidationRegistrationProcess<T extends MssUser> {

    <S extends MssUserDto> ResponseEntity<Collection<String>> controlMssUserRegistrationProcess(S mssUserDto) throws MessagingException;

    <S extends MssUserDto> T mappingDtoToMssUser(S mssUserDto);
}

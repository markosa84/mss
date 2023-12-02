package hu.ak_akademia.mss.login_security_service;

import hu.ak_akademia.mss.model.user.MssSecurityUser;
import hu.ak_akademia.mss.model.user.MssUser;
import hu.ak_akademia.mss.repository.MSSUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MssUserDetailService implements UserDetailsService {

    @Autowired
    public MSSUserRepository mssUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MssUser mssUser = mssUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found " + email));
        return new MssSecurityUser(mssUser);
    }


}

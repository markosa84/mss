package hu.ak_akademia.mss.config;

import hu.ak_akademia.mss.model.user.MssUser;
import hu.ak_akademia.mss.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionMssUser {

    private MssUser currentUser;

    @Autowired
    private RegistrationService registrationService;

    public MssUser getCurrentMssUser() {
        if (currentUser == null) {
            String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
            currentUser = registrationService.getLoggedInUser(userEmail);
        }
        return currentUser;
    }

    public void setCurrentUser(MssUser currentUser) {
        this.currentUser = currentUser;
    }

    public void clearCurrentUser() {
        currentUser = null;
    }
}

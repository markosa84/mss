package hu.ak_akademia.mss.config.jwt;

import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
// ezzel lehetne az application.propertiesből beolvasni adatokat
//@ConfigurationProperties(prefix = "application.jwt")
@Configuration
public class JwtConfig {
    private String secretKey = "ThisIsAVeryVeryVerySecretKeyItIsHardToBroken";
    private String tokenPrefix = "Bearer ";
    private int tokenExpiration = 1;

    public JwtConfig() {
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public int getTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(int tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }

    @Bean
    public SecretKey getSecretKeyForSigning(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String getAuthorizationHeader(){
        return HttpHeaders.AUTHORIZATION;
    }
}

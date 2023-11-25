package hu.ak_akademia.mss.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.ak_akademia.mss.model.user.MssSecurityUser;
import hu.ak_akademia.mss.model.user.MssUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    private final AuthenticationManager authenticationManager;

    private final JwtConfig jwtConfig;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig){
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );
            return authenticationManager.authenticate(authentication);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return super.attemptAuthentication(request, response);
    }

    // TODO lejárati dátumot valami értelmesre!! pl 3 perc) + értelmes key
    // tegyük bele a tokenbe a role-t is!

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        MssSecurityUser securityUser = (MssSecurityUser) authResult.getPrincipal();
        MssUser mssUser = securityUser.getUser();
        String name = mssUser.getFirstName() + " " + mssUser.getLastName();
        String role = mssUser.getRoles();
        String jsonResponse = "{\n\t\"name\": \"" + name + "\",\n\t \"role\": \"" + role + "\"\n}";

        String token = Jwts.builder().subject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .claim("name", authResult.getName())
                .issuedAt(new Date())
                .expiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpiration())))
                .signWith(jwtConfig.getSecretKeyForSigning())
                .compact();
        response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);
        response.getWriter().write(jsonResponse);

    }
}





















package hu.ak_akademia.mss.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.ak_akademia.mss.model.user.MssSecurityUser;
import hu.ak_akademia.mss.model.user.MssUser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private final AuthenticationManager authenticationManager;

    private final JwtConfig jwtConfig;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsernameAndPasswordAuthenticationRequest authReq = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return super.attemptAuthentication(request, response);
    }

    // TODO lejárati dátumot valami értelmesre!! pl 3 perc) + értelmes key

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {

        MssSecurityUser securityUser = (MssSecurityUser) authResult.getPrincipal();
        MssUser mssUser = securityUser.getUser();
        Map<String, Object> responseObject = new LinkedHashMap<>();
        responseObject.put("name", String.format("%s %s", mssUser.getLastName(), mssUser.getFirstName()));
        responseObject.put("roles", List.of(mssUser.getRoles()));
        responseObject.put("userId", mssUser.getUserId());
        String jsonResponse = new ObjectMapper().writeValueAsString(responseObject);
        String token = getTokenFromAuth(authResult);
        if (securityUser.getUser().isActive()) {
            response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().write(jsonResponse);
        } else {
            response.setStatus(403);
            response.getWriter().write("Your account isn't active!");
        }

    }

    private String getTokenFromAuth(Authentication authResult) {
        return Jwts.builder().subject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .claim("name", authResult.getName())
                .issuedAt(new Date())
                .expiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpiration())))
                .signWith(jwtConfig.getSecretKeyForSigning())
                .compact();
    }
}





















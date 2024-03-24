package hu.ak_akademia.mss.config.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;

    public JwtTokenVerifier(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());
        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
            Claims body = getClaims(token);
            List<SimpleGrantedAuthority> simpleGrantedAuthorities = getSimpleGrantedAuthorities(body);
            Authentication authentication = new UsernamePasswordAuthenticationToken(body.getSubject(), null, simpleGrantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e) {
            throw new IllegalStateException("Token can not be truest !!");
        }
        filterChain.doFilter(request, response);
    }

    private Claims getClaims(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(jwtConfig.getSecretKeyForSigning())
                .build()
                .parseSignedClaims(token);
        return claimsJws.getPayload();
    }

    @SuppressWarnings("unchecked")
    @NotNull
    private static List<SimpleGrantedAuthority> getSimpleGrantedAuthorities(Claims body) {
        var authorities = (List<Map<String, String>>) body.get("authorities");
        return authorities.stream().map(m -> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toList());
    }
}

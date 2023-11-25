package hu.ak_akademia.mss.config;

import hu.ak_akademia.mss.config.jwt.JwtConfig;
import hu.ak_akademia.mss.config.jwt.JwtTokenVerifier;
import hu.ak_akademia.mss.config.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import hu.ak_akademia.mss.service.MssUserDetailService;
import hu.ak_akademia.mss.service.PasswordEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    private final MssUserDetailService mssUserDetailService;
    private final JwtConfig jwtConfig;

    public SecurityConfiguration(MssUserDetailService mssUserDetailService, JwtConfig jwtConfig) {
        this.mssUserDetailService = mssUserDetailService;
        this.jwtConfig = jwtConfig;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationManager(addAuthMan())
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(addAuthMan(), jwtConfig))
                .addFilterAfter(new JwtTokenVerifier(jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeHttpRequests(a -> a
                        .antMatchers("/h2-console/**", "/resources/**").permitAll()
                        .antMatchers("/register", "/login/**").permitAll()
                        .antMatchers("/home/**").hasAnyAuthority("ROLE_CLIENT", "ROLE_DOCTOR", "ROLE_ASSISTANT", "ROLE_ADMIN")
                        .antMatchers("/register/doctor", "/register/assistant", "/register/financialColleague")
                        .hasAnyAuthority("ROLE_ASSISTANT", "ROLE_ADMIN")
                        .anyRequest().authenticated())
                //.formLogin()

                //.loginPage("http://localhost:5173/login")
                //.loginProcessingUrl("/login")
                //.passwordParameter("password")
                //.usernameParameter("username")
                //.failureUrl("/failedLogin")
                //.defaultSuccessUrl("/home")
                //.permitAll()
                //.and()
                .cors(cors -> {})

                //.logout(logout -> logout
                //        .logoutUrl("/logout")
                //        .logoutSuccessUrl("/")
                //        .deleteCookies()
                //        .invalidateHttpSession(true)
                //        .permitAll()
                //        .logoutSuccessUrl("/")
                //        .clearAuthentication(true))
                //.headers(h -> h.frameOptions().sameOrigin())
                //.userDetailsService(mssUserDetailService)
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new PasswordEncryption();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:5173"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public AuthenticationManager addAuthMan(){
        return new  ProviderManager(addDaoProvider());
    }
    @Bean
    public AuthenticationProvider addDaoProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(mssUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }



}

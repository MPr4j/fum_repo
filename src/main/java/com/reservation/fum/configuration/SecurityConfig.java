package com.reservation.fum.configuration;

import java.util.Optional;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.reservation.fum.authorities.User;
import com.reservation.fum.components.CustomAuthenticationSucessHandler;
import com.reservation.fum.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        /*
         * Available encryption options
         * BCryptPasswordEncoder—Applies bcrypt strong hashing encryption
         * NoOpPasswordEncoder—Applies no encoding
         * Pbkdf2PasswordEncoder—Applies PBKDF2 encryption
         * SCryptPasswordEncoder—Applies Scrypt hashing encryption
         * StandardPasswordEncoder—Applies SHA-256 hashing encryption
         */
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new CustomAuthenticationSucessHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        log.debug("setting up filterChain ");
        return httpSecurity.authorizeHttpRequests(
                t -> {
                    t.requestMatchers(PathRequest.toH2Console()).permitAll()
                            .requestMatchers("/x").hasRole("USER")
                            .requestMatchers("/", "/**").permitAll()
                            .requestMatchers("/signup/**").permitAll()
                            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
                }).formLogin(t -> {
                    t.loginPage("/login")
                            .loginProcessingUrl("/authenticate")
                            .successHandler(successHandler());
                })
                .csrf(t -> {
                    t.ignoringRequestMatchers("/h2-console/**");
                }).headers(t -> {

                    t.frameOptions().sameOrigin();
                })

                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            var user = userRepo.findByUsername(username);
            return user.orElseThrow(() -> new UsernameNotFoundException(username));
        };
    }
}

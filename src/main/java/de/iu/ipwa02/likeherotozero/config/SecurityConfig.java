package de.iu.ipwa02.likeherotozero.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/backend/**").hasRole("SCIENTIST") // Schütze alle Backend-URLs
                        .anyRequest().permitAll() // Alle anderen URLs sind öffentlich
                )
                .formLogin(withDefaults()) // Standard-Login-Seite von Spring Security
                .logout(logout -> logout.logoutSuccessUrl("/")); // Nach Logout zur Startseite
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Für die Fallstudie reicht ein einfacher In-Memory-Benutzer.
        // In einer echten Anwendung kämen die Benutzer aus einer Datenbank.
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("wissenschaftler")
                .password("passwort123") // Passwort sollte sicher gespeichert werden!
                .roles("SCIENTIST")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
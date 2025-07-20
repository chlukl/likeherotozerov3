package ipwa02.likeherotozero.config;

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
                        .requestMatchers("/backend/**").hasRole("SCIENTIST")
                        .anyRequest().permitAll())
                .formLogin(withDefaults())
                .logout(logout -> logout.logoutSuccessUrl("/"));

        return http.build();
    }



    @Bean
    public UserDetailsService userDetailsService() {
        // In-Memory-Benutzer - kein Benutzer aus DB
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("wissenschaftler")
                .password("passwort123")
                .roles("SCIENTIST")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
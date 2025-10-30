package apl.secure_user_registration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/usuario").hasRole("ADMIN")  // só admin cria
                        .requestMatchers(HttpMethod.DELETE, "/usuario").hasRole("ADMIN") // só admin deleta
                        .requestMatchers(HttpMethod.PUT, "/usuario**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/usuario").hasAnyRole("ADMIN", "USER") // admin e user podem listar
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // ativa autenticação básica (Postman)

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

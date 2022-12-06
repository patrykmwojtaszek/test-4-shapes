package pl.kurs.test4shapes.config;

import org.modelmapper.ModelMapper;
import org.springframework.cglib.core.Converter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.Set;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class ApplicationConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
    }

}

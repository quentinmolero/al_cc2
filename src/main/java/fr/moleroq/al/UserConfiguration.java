package fr.moleroq.al;

import fr.moleroq.al.domain.UserRepository;
import fr.moleroq.al.infrastructure.InMemoryUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }
}

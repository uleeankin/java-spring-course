package ru.rsreu.photostudio.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.rsreu.photostudio.models.Services;
import ru.rsreu.photostudio.models.User;
import ru.rsreu.photostudio.repositories.ServiceRepository;
import ru.rsreu.photostudio.repositories.UserRepository;

//@Profile("!prod")
@Configuration
public class DatabaseConfig {

    @Bean
    public CommandLineRunner dataLoader(ServiceRepository serviceRepository,
                                        UserRepository userRepository,
                                        PasswordEncoder encoder) {
        return args -> {
            serviceRepository.save(new Services("STLO", "Studio", Services.Type.LOCATION));
            serviceRepository.save(new Services("NALO", "Nature", Services.Type.LOCATION));
            serviceRepository.save(new Services("CILO", "City", Services.Type.LOCATION));
            serviceRepository.save(new Services("FAFA", "Family", Services.Type.FASHION));
            serviceRepository.save(new Services("CUFA", "Customise", Services.Type.FASHION));
            serviceRepository.save(new Services("CAFA", "Casual", Services.Type.FASHION));
            serviceRepository.save(new Services("POSH", "Portrait", Services.Type.SHOT));
            serviceRepository.save(new Services("GESH", "General", Services.Type.SHOT));
            serviceRepository.save(new Services("MESH", "Medium", Services.Type.SHOT));
            serviceRepository.save(new Services("PHVI", "Photo", Services.Type.VIEW));
            serviceRepository.save(new Services("VIVI", "Video", Services.Type.VIEW));
            serviceRepository.save(new Services("BOVI", "Both", Services.Type.VIEW));

            userRepository.save(new User("ule", encoder.encode("1234"),
                    "Julia Sokolova",  "123-123-1234"));
        };
    }
}

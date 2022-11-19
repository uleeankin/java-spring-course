package ru.rsreu.photostudio.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.rsreu.photostudio.models.PhotoSession;
import ru.rsreu.photostudio.models.Services;
import ru.rsreu.photostudio.models.User;
import ru.rsreu.photostudio.repositories.PhotoSessionRepository;
import ru.rsreu.photostudio.repositories.ServiceRepository;
import ru.rsreu.photostudio.repositories.UserRepository;

import java.util.Arrays;

@Profile("!prod")
@Configuration
public class DatabaseConfig {

    @Bean
    public CommandLineRunner dataLoader(ServiceRepository serviceRepository,
                                        UserRepository userRepository,
                                        PhotoSessionRepository photoSessionRepository,
                                        PasswordEncoder encoder) {

        return args -> {
            Services studioLocation = new Services("STLO", "Studio", Services.Type.LOCATION);
            Services natureLocation = new Services("NALO", "Nature", Services.Type.LOCATION);
            Services cityLocation = new Services("CILO", "City", Services.Type.LOCATION);
            Services familyFashion = new Services("FAFA", "Family", Services.Type.FASHION);
            Services castomiseFashion = new Services("CUFA", "Customise", Services.Type.FASHION);
            Services casualFashion = new Services("CAFA", "Casual", Services.Type.FASHION);
            Services portraitShot = new Services("POSH", "Portrait", Services.Type.SHOT);
            Services generalShot = new Services("GESH", "General", Services.Type.SHOT);
            Services mediumShot = new Services("MESH", "Medium", Services.Type.SHOT);
            Services photoView = new Services("PHVI", "Photo", Services.Type.VIEW);
            Services videoView = new Services("VIVI", "Video", Services.Type.VIEW);
            Services bothView = new Services("BOVI", "Both", Services.Type.VIEW);

            serviceRepository.save(studioLocation);
            serviceRepository.save(natureLocation);
            serviceRepository.save(cityLocation);
            serviceRepository.save(familyFashion);
            serviceRepository.save(castomiseFashion);
            serviceRepository.save(casualFashion);
            serviceRepository.save(portraitShot);
            serviceRepository.save(generalShot);
            serviceRepository.save(mediumShot);
            serviceRepository.save(photoView);
            serviceRepository.save(videoView);
            serviceRepository.save(bothView);

            PhotoSession photoSession1 = new PhotoSession();
            photoSession1.setName("FirstPhotoSession");
            photoSession1.setServices(Arrays.asList(
                    studioLocation,
                    familyFashion,
                    portraitShot,
                    photoView
            ));
            PhotoSession photoSession2 = new PhotoSession();
            photoSession2.setName("SecondPhotoSession");
            photoSession2.setServices(Arrays.asList(
                    cityLocation,
                    casualFashion,
                    mediumShot,
                    bothView
            ));
            PhotoSession photoSession3 = new PhotoSession();
            photoSession3.setName("ThirdPhotoSession");
            photoSession3.setServices(Arrays.asList(
                    natureLocation,
                    castomiseFashion,
                    generalShot,
                    videoView
            ));

            photoSessionRepository.save(photoSession1);
            photoSessionRepository.save(photoSession2);
            photoSessionRepository.save(photoSession3);

            userRepository.save(new User("ule", encoder.encode("1234"),
                    "Julia Sokolova",  "123-123-1234"));
        };
    }
}

package ru.rsreu.photostudio.controllers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.rsreu.photostudio.models.Services.*;
import ru.rsreu.photostudio.models.Services;
import ru.rsreu.photostudio.repositories.ServiceRepository;

@Controller
public class PhotoStudioHomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @Bean
    public CommandLineRunner dataLoader(ServiceRepository serviceRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                serviceRepository.save(new Services("STLO", "Studio", Type.LOCATION));
                serviceRepository.save(new Services("NALO", "Nature", Type.LOCATION));
                serviceRepository.save(new Services("CILO", "City", Type.LOCATION));
                serviceRepository.save(new Services("FAFA", "Family", Type.FASHION));
                serviceRepository.save(new Services("CUFA", "Customise", Type.FASHION));
                serviceRepository.save(new Services("CAFA", "Casual", Type.FASHION));
                serviceRepository.save(new Services("POSH", "Portrait", Type.SHOT));
                serviceRepository.save(new Services("GESH", "General", Type.SHOT));
                serviceRepository.save(new Services("MESH", "Medium", Type.SHOT));
                serviceRepository.save(new Services("PHVI", "Photo", Type.VIEW));
                serviceRepository.save(new Services("VIVI", "Video", Type.VIEW));
                serviceRepository.save(new Services("BOVI", "Both", Type.VIEW));
            }
        };
    }
}

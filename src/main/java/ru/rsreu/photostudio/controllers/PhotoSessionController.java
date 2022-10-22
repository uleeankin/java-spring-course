package ru.rsreu.photostudio.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.rsreu.photostudio.models.Order;
import ru.rsreu.photostudio.models.PhotoSession;
import ru.rsreu.photostudio.models.Services;
import ru.rsreu.photostudio.models.Services.Type;
import ru.rsreu.photostudio.models.User;
import ru.rsreu.photostudio.repositories.PhotoSessionRepository;
import ru.rsreu.photostudio.repositories.ServiceRepository;
import ru.rsreu.photostudio.repositories.UserRepository;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/services")
@SessionAttributes("order")
public class PhotoSessionController {

    private final ServiceRepository serviceRepository;
    private final PhotoSessionRepository photoSessionRepository;
    private final UserRepository userRepository;

    @Autowired
    public PhotoSessionController(ServiceRepository serviceRepository,
                                  PhotoSessionRepository photoSessionRepository,
                                  UserRepository userRepository) {
        this.serviceRepository = serviceRepository;
        this.photoSessionRepository = photoSessionRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "services")
    public PhotoSession design() {
        return new PhotoSession();
    }


    @GetMapping
    public String showServicesPage(Model model, Principal principal) {
        List<Services> services = new ArrayList<>();
        serviceRepository.findAll().forEach(services::add);

        Type[] types = Services.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(services, type));
        }

        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);

        return "services";
    }

    @PostMapping
    public String processDesign(@Valid PhotoSession photoSession,
                                Errors error,
                                @ModelAttribute Order order) {
        if (error.hasErrors()) {
            return "services";
        }

        PhotoSession savedPhotoSession = photoSessionRepository.save(photoSession);
        order.addPhotoSession(savedPhotoSession);

        //log.info(String.format("Processing design: %s", photoSession));

        return "redirect:orders/current";
    }

    private List<Services> filterByType(
            List<Services> services, Type type) {
        return services
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}

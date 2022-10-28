package ru.rsreu.photostudio.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.rsreu.photostudio.UDT.PhotoSessionUDT;
import ru.rsreu.photostudio.models.Order;
import ru.rsreu.photostudio.models.PhotoSession;
import ru.rsreu.photostudio.models.Services;
import ru.rsreu.photostudio.models.Services.Type;
import ru.rsreu.photostudio.repositories.ServiceRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/services")
@SessionAttributes("order")
public class PhotoSessionController {

    private final ServiceRepository serviceRepository;

    @Autowired
    public PhotoSessionController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @ModelAttribute
    public void addServicesToModel(Model model) {
        List<Services> services = new ArrayList<>();
        serviceRepository.findAll().forEach(services::add);

        Type[] types = Services.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(services, type));
        }
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "photoSession")
    public PhotoSession photoSession() {
        return new PhotoSession();
    }

    @GetMapping
    public String showServicesPage() {
        return "services";
    }

    @PostMapping
    public String processDesign(@Valid PhotoSession photoSession,
                                Errors error,
                                @ModelAttribute Order order) {

        if (error.hasErrors()) {
            return "services";
        }

        order.addPhotoSession(new PhotoSessionUDT(
                photoSession.getName(),
                photoSession.getServices()
        ));

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

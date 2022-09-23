package ru.rsreu.photostudio.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rsreu.photostudio.models.PhotoSession;
import ru.rsreu.photostudio.models.Services;
import ru.rsreu.photostudio.models.Services.Type;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/services")
public class PhotoSessionController {

    @ModelAttribute
    public void addPhotoSessionServices(Model model) {
        List<Services> services = Arrays.asList(
            new Services("STLO", "Studio", Type.LOCATION),
            new Services("NALO", "Nature", Type.LOCATION),
            new Services("CILO", "City", Type.LOCATION),
            new Services("FAFA", "Family", Type.FASHION),
            new Services("CUFA", "Customise", Type.FASHION),
            new Services("CAFA", "Casual", Type.FASHION),
            new Services("POSH", "Portrait", Type.SHOT),
            new Services("GESH", "General", Type.SHOT),
            new Services("MESH", "Medium", Type.SHOT),
            new Services("PHVI", "Photo", Type.VIEW),
            new Services("VIVI", "Video", Type.VIEW),
            new Services("BOVI", "Both", Type.VIEW)
        );

        Type[] types = Services.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(services, type));

        }
    }



    @GetMapping
    public String showServicesPage(Model model) {
        model.addAttribute("services", new PhotoSession());
        return "services";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("services") PhotoSession services,
                                Errors error, Model model) {
        if (error.hasErrors()) {
            return "services";
        }

        log.info("Processing design: " + services);

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

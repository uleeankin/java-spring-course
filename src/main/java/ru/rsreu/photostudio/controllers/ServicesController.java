package ru.rsreu.photostudio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rsreu.photostudio.models.Services;
import ru.rsreu.photostudio.repositories.ServiceRepository;

@RestController
@RequestMapping(path="/api/services", produces="application/json")
@CrossOrigin(origins="http://localhost:8081")
public class ServicesController {

    private ServiceRepository serviceRepository;

    @Autowired
    public ServicesController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping
    public Iterable<Services> getAllServices() {
        return serviceRepository.findAll();
    }
}

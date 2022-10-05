package ru.rsreu.photostudio.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.rsreu.photostudio.models.Services;
import ru.rsreu.photostudio.repositories.ServiceRepository;

import java.util.Optional;


@Component
public class ServiceByIdConverter implements Converter<String, Services> {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceByIdConverter(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Services convert(String serviceId) {
        Optional<Services> service = serviceRepository.findById(serviceId);
        return service.orElse(null);
    }
}

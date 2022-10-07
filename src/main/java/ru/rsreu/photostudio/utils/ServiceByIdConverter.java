package ru.rsreu.photostudio.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.rsreu.photostudio.models.Services;
import ru.rsreu.photostudio.repositories.ServicesRepository;

import java.util.Optional;


@Component
public class ServiceByIdConverter implements Converter<String, Services> {

    private final ServicesRepository serviceRepository;

    @Autowired
    public ServiceByIdConverter(ServicesRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Services convert(String serviceId) {
        return this.serviceRepository.findById(serviceId);
    }
}

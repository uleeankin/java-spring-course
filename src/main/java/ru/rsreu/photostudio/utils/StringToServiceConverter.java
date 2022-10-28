package ru.rsreu.photostudio.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.rsreu.photostudio.UDT.ServicesUDT;
import ru.rsreu.photostudio.models.Services;
import ru.rsreu.photostudio.repositories.ServiceRepository;

import java.util.Optional;

@Component
public class StringToServiceConverter implements Converter<String, ServicesUDT> {

    private ServiceRepository serviceRepository;

    public StringToServiceConverter(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public ServicesUDT convert(String id) {
        Optional<Services> service = serviceRepository.findById(id);
        if (!service.isPresent()) {
            return null;
        }

        return service.map(i -> {
            return new ServicesUDT(i.getName(), i.getType());
        }).get();
    }
}

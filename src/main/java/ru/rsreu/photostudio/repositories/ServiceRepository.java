package ru.rsreu.photostudio.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import ru.rsreu.photostudio.models.Services;

@CrossOrigin(origins = "http://localhost:8081")
public interface ServiceRepository
        extends CrudRepository<Services, String> {
}

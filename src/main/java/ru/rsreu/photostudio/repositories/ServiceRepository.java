package ru.rsreu.photostudio.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.rsreu.photostudio.models.Services;

public interface ServiceRepository
        extends CrudRepository<Services, String> {
}

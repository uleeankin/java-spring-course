package ru.rsreu.photostudio.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import ru.rsreu.photostudio.models.Services;

public interface ServiceRepository
        extends MongoRepository<Services, String> {
}

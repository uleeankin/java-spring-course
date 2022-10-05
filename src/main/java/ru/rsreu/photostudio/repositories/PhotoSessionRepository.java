package ru.rsreu.photostudio.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.rsreu.photostudio.models.PhotoSession;

public interface PhotoSessionRepository
        extends CrudRepository<PhotoSession, Long> {
}

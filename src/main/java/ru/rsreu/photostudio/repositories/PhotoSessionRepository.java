package ru.rsreu.photostudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.rsreu.photostudio.models.PhotoSession;

public interface PhotoSessionRepository
        extends JpaRepository<PhotoSession, Long> {
}

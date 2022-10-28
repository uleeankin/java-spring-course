package ru.rsreu.photostudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.rsreu.photostudio.models.Services;

public interface ServiceRepository
        extends JpaRepository<Services, String> {
}

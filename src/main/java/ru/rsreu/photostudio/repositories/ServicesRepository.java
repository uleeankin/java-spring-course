package ru.rsreu.photostudio.repositories;

import ru.rsreu.photostudio.models.Services;

public interface ServicesRepository {
    Iterable<Services> findAll();
    Services findById(String id);
    Services save(Services services);
}

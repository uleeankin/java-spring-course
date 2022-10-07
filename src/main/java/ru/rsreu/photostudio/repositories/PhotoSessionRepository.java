package ru.rsreu.photostudio.repositories;

import ru.rsreu.photostudio.models.PhotoSession;

public interface PhotoSessionRepository {
    PhotoSession save(PhotoSession photoSession);
}

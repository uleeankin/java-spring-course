package ru.rsreu.photostudio.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.rsreu.photostudio.models.PhotoSession;

public interface PhotoSessionRepository
        extends PagingAndSortingRepository<PhotoSession, Long> {
}

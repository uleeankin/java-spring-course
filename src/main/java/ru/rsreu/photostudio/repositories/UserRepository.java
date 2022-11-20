package ru.rsreu.photostudio.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.rsreu.photostudio.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}

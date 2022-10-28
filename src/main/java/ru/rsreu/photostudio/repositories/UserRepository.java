package ru.rsreu.photostudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rsreu.photostudio.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

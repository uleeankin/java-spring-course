package ru.rsreu.photostudio.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.rsreu.photostudio.models.Order;
import ru.rsreu.photostudio.models.User;

import java.util.List;

public interface OrderRepository
        extends JpaRepository<Order, Long> {
    List<Order> findByUserOrderByPlacedAtDesc(
            User user, Pageable pageable);
}

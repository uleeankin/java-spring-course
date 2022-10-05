package ru.rsreu.photostudio.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.rsreu.photostudio.models.Order;

public interface OrderRepository
        extends CrudRepository<Order, Long> {
}

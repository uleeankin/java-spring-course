package ru.rsreu.photostudio.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.rsreu.photostudio.models.Order;

import java.util.UUID;

public interface OrderRepository
        extends CrudRepository<Order, UUID> {
}

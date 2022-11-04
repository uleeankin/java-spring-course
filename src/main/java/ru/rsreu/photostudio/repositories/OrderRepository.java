package ru.rsreu.photostudio.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import ru.rsreu.photostudio.models.Order;

public interface OrderRepository
        extends MongoRepository<Order, String> {
}

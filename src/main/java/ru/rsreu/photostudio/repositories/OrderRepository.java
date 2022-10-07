package ru.rsreu.photostudio.repositories;

import ru.rsreu.photostudio.models.Order;

public interface OrderRepository {
    Order save(Order order);
}

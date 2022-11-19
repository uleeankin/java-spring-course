package ru.rsreu.photostudio.api.restfull_controllers;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.rsreu.photostudio.models.Order;
import ru.rsreu.photostudio.repositories.OrderRepository;

@RestController
@RequestMapping(path = "/api/orders",
        produces = "application/json")
@CrossOrigin(origins = "http://localhost:8081")
public class RestOrderController {

    private final OrderRepository orderRepository;

    public RestOrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PutMapping(path = "/{orderId}",
            consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Order putOrder(
            @PathVariable Long orderId,
            @RequestBody Order order
    ) {
        order.setId(orderId);
        return orderRepository.save(order);
    }

    @PatchMapping(path="/{orderId}", consumes="application/json")
    public Order patchOrder(@PathVariable("orderId") Long orderId,
                                @RequestBody Order patch) {

        Order order = orderRepository.findById(orderId).get();
        if (patch.getName() != null) {
            order.setName(patch.getName());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return orderRepository.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {

        }
    }
}

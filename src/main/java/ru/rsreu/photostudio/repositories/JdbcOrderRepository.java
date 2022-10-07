package ru.rsreu.photostudio.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.rsreu.photostudio.models.Order;
import ru.rsreu.photostudio.models.PhotoSession;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderPhotoSessionInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.orderInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Photo_Session_Order")
                .usingGeneratedKeyColumns("id");
        this.orderPhotoSessionInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Photo_Session_Order_Photo_Sessions");
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {
        order.setPlacedAt(new Date());
        long orderId = saveOrderDetails(order);
        order.setId(orderId);
        List<PhotoSession> photoSessions = order.getPhotoSessions();

        for (PhotoSession photoSession : photoSessions) {
            savePhotoSessionToOrder(photoSession, orderId);
        }

        return order;
    }

    private void savePhotoSessionToOrder(PhotoSession photoSession,
                                         long orderId) {
        Map<String, Object> values = new HashMap<>();
        values.put("photoSessionOrder", orderId);
        values.put("photoSession", photoSession.getId());
        orderPhotoSessionInserter.execute(values);
    }

    private long saveOrderDetails(Order order) {
        Map<String, Object> values =
                objectMapper.convertValue(order, Map.class);
        values.put("placedAt", order.getPlacedAt());

        return orderInserter
                .executeAndReturnKey(values)
                .longValue();
    }

}

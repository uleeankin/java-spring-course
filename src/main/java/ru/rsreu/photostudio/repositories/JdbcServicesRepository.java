package ru.rsreu.photostudio.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.rsreu.photostudio.models.Services;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcServicesRepository implements ServicesRepository {

    private static final String ALL_SERVICES_QUERY = "select id, name, type from Services";
    private static final String ONE_OF_SERVICES_QUERY = "select id, name, type from Services where id=?";
    private static final String SAVE_SERVICE_QUERY = "insert into Services (id, name, type) values (?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcServicesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Services> findAll() {
        return this.jdbcTemplate.query(ALL_SERVICES_QUERY,
                this::mapRowToServices);
    }

    @Override
    public Services findById(String id) {
        return this.jdbcTemplate.queryForObject(
                ONE_OF_SERVICES_QUERY,
                this::mapRowToServices, id);
    }

    @Override
    public Services save(Services services) {
        this.jdbcTemplate.update(
            SAVE_SERVICE_QUERY,
            services.getId(),
            services.getName(),
            services.getType().toString());
        return services;
    }

    private Services mapRowToServices(ResultSet resultSet,
                                int rowNumber) throws SQLException {
        return new Services(
                resultSet.getString("id"),
                resultSet.getString("name"),
                Services.Type.valueOf(resultSet.getString("type")));
    }

}

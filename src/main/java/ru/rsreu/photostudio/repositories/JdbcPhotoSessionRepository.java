package ru.rsreu.photostudio.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.rsreu.photostudio.models.PhotoSession;
import ru.rsreu.photostudio.models.Services;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Repository
public class JdbcPhotoSessionRepository
        implements PhotoSessionRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPhotoSessionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PhotoSession save(PhotoSession photoSession) {
        long photoSessionId = savePhotoSessionInfo(photoSession);
        photoSession.setId(photoSessionId);
        for (Services services : photoSession.getServices()) {
            saveServiceToPhotoSession(services, photoSessionId);
        }
        return photoSession;
    }

    private long savePhotoSessionInfo(PhotoSession photoSession) {
        photoSession.setCreatedAt(new Date());

        PreparedStatementCreatorFactory factory =
                new PreparedStatementCreatorFactory(
                        "insert into Photo_Session (name, createdAt) values (?, ?)",
                        Types.VARCHAR, Types.TIMESTAMP);
        factory.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc = factory.newPreparedStatementCreator(
                        Arrays.asList(
                            photoSession.getName(),
                            new Timestamp(photoSession.getCreatedAt().getTime())));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    private void saveServiceToPhotoSession(
            Services services, long photoSessionId) {
        jdbcTemplate.update(
                "insert into Photo_Session_Services (photoSession, services) values (?, ?)",
                photoSessionId, services.getId());
    }

}

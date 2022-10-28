package ru.rsreu.photostudio.models;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import ru.rsreu.photostudio.UDT.ServicesUDT;
import ru.rsreu.photostudio.utils.PhotoSessionUDRUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Table("photosessions")
public class PhotoSession {

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private UUID id = Uuids.timeBased();

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @Size(min = 1, message = "You must choose at least 1 photo session service")
    @Column("services")
    private List<ServicesUDT> services = new ArrayList<>();

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED,
                        ordering = Ordering.DESCENDING)
    private Date createdAt = new Date();

    public void addService(Services service) {
        this.services.add(PhotoSessionUDRUtils.toServiceUDT(service));
    }
}

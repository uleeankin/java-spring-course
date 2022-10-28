package ru.rsreu.photostudio.models;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Table("services")
public class Services {

    @PrimaryKey
    private String id;
    private String name;
    private Type type;

    public enum Type {
        LOCATION,
        FASHION,
        SHOT,
        VIEW
    }

}

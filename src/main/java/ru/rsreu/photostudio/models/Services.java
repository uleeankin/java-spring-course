package ru.rsreu.photostudio.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class Services {

    @Id
    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        LOCATION,
        FASHION,
        SHOT,
        VIEW
    }

}

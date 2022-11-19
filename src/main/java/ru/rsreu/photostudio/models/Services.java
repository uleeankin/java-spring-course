package ru.rsreu.photostudio.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@RestResource(path = "services", rel = "services")
public class Services {

    @Id
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        LOCATION,
        FASHION,
        SHOT,
        VIEW
    }

}

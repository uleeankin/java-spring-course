package ru.rsreu.photostudio.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Services {

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

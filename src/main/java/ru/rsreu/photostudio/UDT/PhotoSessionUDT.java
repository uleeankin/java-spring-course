package ru.rsreu.photostudio.UDT;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.List;

@Data
@UserDefinedType("photosession")
public class PhotoSessionUDT {

    private final String name;

    private final List<ServicesUDT> services;
}

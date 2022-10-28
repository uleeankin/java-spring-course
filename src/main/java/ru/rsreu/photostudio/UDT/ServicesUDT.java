package ru.rsreu.photostudio.UDT;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import ru.rsreu.photostudio.models.Services;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@UserDefinedType("service")
public class ServicesUDT {

    private final String name;

    private final Services.Type type;
}

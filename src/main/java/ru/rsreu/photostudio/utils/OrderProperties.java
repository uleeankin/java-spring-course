package ru.rsreu.photostudio.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Component
@ConfigurationProperties(prefix = "photo.session.order")
@Data
@Validated
public class OrderProperties {

    @Min(value=1, message="must be between 1 and 25")
    @Max(value=25, message="must be between 1 and 25")
    private int pageSize = 20;

}

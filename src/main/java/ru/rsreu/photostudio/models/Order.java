package ru.rsreu.photostudio.models;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import ru.rsreu.photostudio.UDT.PhotoSessionUDT;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Table("orders")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @PrimaryKey
    private UUID id = Uuids.timeBased();

    @NotBlank(message="Name is required")
    private String name;

    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp="^(0?[1-9]|[12][0-9]|3[01])([\\/])(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted DD/MM/YY")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    private Date placedAt = new Date();

    @Column("photosessions")
    private List<PhotoSessionUDT> photoSessions = new ArrayList<>();

    public void addPhotoSession(PhotoSessionUDT photoSession) {
        this.photoSessions.add(photoSession);
    }
}

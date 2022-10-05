package ru.rsreu.photostudio.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class PhotoSession {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @ManyToMany(targetEntity = Services.class)
    @Size(min = 1, message = "You must choose at least 1 photo session service")
    private List<Services> services = new ArrayList<>();

    private Date createdAt;

    @PrePersist
    private void createdAt() {
        this.createdAt = new Date();
    }
}

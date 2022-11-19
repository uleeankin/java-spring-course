package ru.rsreu.photostudio.api.restfull_controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rsreu.photostudio.models.PhotoSession;
import ru.rsreu.photostudio.repositories.PhotoSessionRepository;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/photo",
                produces = "application/json")
@CrossOrigin(origins = "http://localhost:8081")
public class RestPhotoSessionController {

    private final PhotoSessionRepository photoSessionRepository;

    public RestPhotoSessionController(PhotoSessionRepository photoSessionRepository) {
        this.photoSessionRepository = photoSessionRepository;
    }

    //http://localhost:8081/api/photo?recent
    @GetMapping(params = "recent")
    public Iterable<PhotoSession> getRecentPhotoSessions() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        return photoSessionRepository.findAll(page).getContent();
    }

    //http://localhost:8081/api/photo/2
    @GetMapping(path = "/{id}")
    public ResponseEntity<PhotoSession> getPhotoSessionById(@PathVariable("id") Long id) {
        Optional<PhotoSession> photoSession = photoSessionRepository.findById(id);
        System.out.println("sdkjfdkjfh");
        return photoSession.map(
                session -> new ResponseEntity<>(
                                session, HttpStatus.OK)
        ).orElseGet(() -> new ResponseEntity<>(
                                null, HttpStatus.BAD_REQUEST));
    }

    //http://localhost:8081/api/photo
    /*
    * {
    "id": 5,
    "name": "FifthPhotoSession",
    "services": [
        {
            "id": "CILO",
            "name": "City",
            "type": "LOCATION"
        },
        {
            "id": "CAFA",
            "name": "Casual",
            "type": "FASHION"
        },
        {
            "id": "MESH",
            "name": "Medium",
            "type": "SHOT"
        },
        {
            "id": "BOVI",
            "name": "Both",
            "type": "VIEW"
        }
    ],
    "createdAt": "2022-11-20T01:18:07.344+00:00"
}*/

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public PhotoSession postPhotoSession(@RequestBody PhotoSession photoSession) {
        return photoSessionRepository.save(photoSession);
    }
}

package ru.rsreu.photostudio.rest_client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.rsreu.photostudio.models.PhotoSession;
import ru.rsreu.photostudio.models.Services;

import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class PhotoSessionClient {

    private final RestTemplate restTemplate;
    private final Traverson traverson;

    public PhotoSessionClient(RestTemplate restTemplate, Traverson traverson) {
        this.restTemplate = restTemplate;
        this.traverson = traverson;
    }

    public Services getServiceById(String serviceId) {
        return restTemplate.getForObject(
                "http://localhost:8081/services/{id}",
                    Services.class, serviceId
        );
    }

    public List<Services> getAllServices() {
        return restTemplate.exchange("http://localhost:8081/services",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Services>>() {})
                .getBody();
    }

    public void updateIngredient(Services services) {
        restTemplate.put("http://localhost:8081/services/{id}",
                services, services.getId());
    }

    public Services createService(Services service) {
        return restTemplate.postForObject("http://localhost:8081/services",
                service, Services.class);
    }

    public void deleteService(Services service) {
        restTemplate.delete("http://localhost:8081/services/{id}",
                service.getId());
    }

    public Iterable<Services> getAllServicesWithTraverson() {
        ParameterizedTypeReference<CollectionModel<Services>> serviceType =
                new ParameterizedTypeReference<CollectionModel<Services>>() {};

        CollectionModel<Services> ingredientRes =
                traverson
                        .follow("services")
                        .toObject(serviceType);

        Collection<Services> services = ingredientRes.getContent();
        return services;
    }

    public Services addService(Services service) {
        String servicesUrl = traverson
                .follow("services")
                .asLink()
                .getHref();

        return restTemplate.postForObject(servicesUrl,
                service,
                Services.class);
    }

    public Iterable<PhotoSession> getRecentPhotoSessionsWithTraverson() {
        ParameterizedTypeReference<CollectionModel<PhotoSession>> photoSessionType =
                new ParameterizedTypeReference<CollectionModel<PhotoSession>>() {};

        CollectionModel<PhotoSession> photoSessionRes =
                traverson
                        .follow("photos")
                        .follow("recent")
                        .toObject(photoSessionType);

        Collection<PhotoSession> photoSessions = photoSessionRes.getContent();

        return photoSessions;
    }
}

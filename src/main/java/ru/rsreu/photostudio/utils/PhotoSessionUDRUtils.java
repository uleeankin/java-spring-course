package ru.rsreu.photostudio.utils;

import ru.rsreu.photostudio.UDT.PhotoSessionUDT;
import ru.rsreu.photostudio.UDT.ServicesUDT;
import ru.rsreu.photostudio.models.PhotoSession;
import ru.rsreu.photostudio.models.Services;

import java.util.List;
import java.util.stream.Collectors;

public class PhotoSessionUDRUtils {

    public static PhotoSessionUDT toPhotoSessionUDT(
            PhotoSession photoSession) {
        return new PhotoSessionUDT(photoSession.getName(),
                                    photoSession.getServices());
    }

    public static List<ServicesUDT> toServiceUDTs(List<Services> services) {
        return services.stream()
                .map(PhotoSessionUDRUtils::toServiceUDT)
                .collect(Collectors.toList());
    }

    public static ServicesUDT toServiceUDT(Services service) {
        return new ServicesUDT(service.getName(),
                                    service.getType());
    }
}

package ru.rsreu.photostudio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PhotoStudioHomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}

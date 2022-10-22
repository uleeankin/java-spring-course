package ru.rsreu.photostudio.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.rsreu.photostudio.models.User;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String fullName;
    private String phoneNumber;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username,
                passwordEncoder.encode(password),
                fullName,
                phoneNumber);
    }
}

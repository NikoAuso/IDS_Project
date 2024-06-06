package it.unicam.cs.ids.observer;

import it.unicam.cs.ids.model.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class ObserverImpl implements Observer{
    public void update(Users user, String message) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Authenticated user: {}", authentication.getName());
        log.info("Authorities: {}", authentication.getAuthorities());

        log.info("Notifica inviata a {} - {}", user.getUsername(), message);
    }
}
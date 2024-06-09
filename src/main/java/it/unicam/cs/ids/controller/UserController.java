package it.unicam.cs.ids.controller;

import it.unicam.cs.ids.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/api/user/preferiti/{poiId}")
    public ResponseEntity<?> addPOIToPreferiti(@PathVariable Long poiId) {
        try {
            log.info("Utente: {}", userService.getAuthenticatedUserId());
            return ResponseEntity.ok(userService.addPOIToPreferiti(poiId, userService.getAuthenticatedUserId()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }
}

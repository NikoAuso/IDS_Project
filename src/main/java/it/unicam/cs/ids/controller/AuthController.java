package it.unicam.cs.ids.controller;

import it.unicam.cs.ids.dto.UserRegistrationDto;
import it.unicam.cs.ids.model.Users;
import it.unicam.cs.ids.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping()
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Basic")) {
            // Estrai le credenziali codificate in base64
            String base64Credentials = authHeader.substring("Basic".length()).trim();
            // Decodifica le credenziali
            String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
            // Le credenziali sono nel formato "username:password"
            final String[] values = credentials.split(":", 2);

            log.info("Login request: username={}, password={}", values[0], values[1]);
            Authentication authenticationRequest =
                    new UsernamePasswordAuthenticationToken(values[0], values[1]);
            Authentication authenticationResponse =
                    this.authenticationManager.authenticate(authenticationRequest);
            return ResponseEntity.ok(authenticationResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or incorrect 'Authorization' header.");
        }
    }

    @PostMapping("/registrazione")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRegistrationDto registrazioneDto,
                                               BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Si sono verificati errori di validazione: " + result.getAllErrors());
        }

        try {
            Users user = userService.create(registrazioneDto);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }
}

package it.unicam.cs.ids.controller;

import it.unicam.cs.ids.dto.ComuneDto;
import it.unicam.cs.ids.dto.NewUserDto;
import it.unicam.cs.ids.dto.RuoloUpdateDto;
import it.unicam.cs.ids.dto.UserRegistrationDto;
import it.unicam.cs.ids.enumeration.Ruoli;
import it.unicam.cs.ids.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/api/user/all")
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.read());
        }catch (Exception e){
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/api/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.read(id));
        }catch (Exception e){
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/api/user/create")
    public ResponseEntity<?> createUser(@RequestBody @Valid NewUserDto newUserDto, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        try {
            return ResponseEntity.ok(userService.create(newUserDto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/api/user/curatore/{userId}/create")
    public ResponseEntity<?> createCuratoreUser(@PathVariable Long userId, @RequestBody @Valid ComuneDto comuneDto, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        try {
            return ResponseEntity.ok(userService.createCuratore(userId, comuneDto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/api/user/{id}/ruolo-update")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody @Valid RuoloUpdateDto ruolo, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        try {
            return ResponseEntity.ok(userService.updateRuolo(id, ruolo));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('TURISTA', 'CONTRIBUTOR', 'CURATORE', 'ANIMATORE')")
    @PostMapping("/api/user/preferiti/{poiId}")
    public ResponseEntity<?> addPOIToPreferiti(@PathVariable Long poiId) {
        try {
            return ResponseEntity.ok(userService.addPOIToPreferiti(poiId, userService.getAuthenticatedUser()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('TURISTA', 'CONTRIBUTOR', 'CURATORE', 'ANIMATORE')")
    @DeleteMapping("/api/user/preferiti/{poiId}")
    public ResponseEntity<?> removePOIFromPreferiti(@PathVariable Long poiId) {
        try {
            return ResponseEntity.ok(userService.removePOIFromPreferiti(poiId, userService.getAuthenticatedUser()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }

    }
}

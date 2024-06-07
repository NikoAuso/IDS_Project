package it.unicam.cs.ids.controller;

import it.unicam.cs.ids.dto.ItinerarioDto;
import it.unicam.cs.ids.model.Itinerario;
import it.unicam.cs.ids.services.ItinerarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItinerarioController {

    @Autowired
    private ItinerarioService itinerarioService;

    @GetMapping("/itinerari/all")
    public ResponseEntity<?> getAllItinerari() {
        List<Itinerario> itinerari = itinerarioService.getAll();
        try {
            if (itinerari.isEmpty())
                return ResponseEntity.ok("Nessun itinerario trovato nel database.");
            else
                return ResponseEntity.ok(itinerari);
        }catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @GetMapping("/itinerari/{id}")
    public ResponseEntity<?> readItinerarioById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(itinerarioService.read(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PostMapping("/api/itinerari")
    public ResponseEntity<?> createItinerario(@RequestBody @Valid ItinerarioDto itinerarioDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Si sono verificati errori di validazione: " + result.getAllErrors());
        }

        try {
            return ResponseEntity.ok(itinerarioService.create(itinerarioDto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PutMapping("/api/itinerari/{id}")
    public ResponseEntity<?> updateItinerario(@PathVariable Long id, @RequestBody @Valid ItinerarioDto itinerarioAggiornato, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Si sono verificati errori di validazione: " + result.getAllErrors());
        }

        try {
            return ResponseEntity.ok(itinerarioService.update(id, itinerarioAggiornato));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @DeleteMapping("/api/itinerari/{id}")
    public ResponseEntity<?> deleteItinerario(@PathVariable Long id) {
        try {
            itinerarioService.delete(id);
            return ResponseEntity.ok("Comune eliminato con successo.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PostMapping("/api/itinerari/{itinerarioId}/{poiId}")
    public ResponseEntity<Void> addPOI(@PathVariable Long itinerarioId, @PathVariable Long poiId) {
        try {
            itinerarioService.addPointOfInterest(itinerarioId, poiId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/itinerari/{itinerarioId}/{poiId}")
    public ResponseEntity<Void> removePOI(@PathVariable Long itinerarioId, @PathVariable Long poiId) {
        try {
            itinerarioService.removePointOfInterest(itinerarioId, poiId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}


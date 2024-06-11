package it.unicam.cs.ids.controller;

import it.unicam.cs.ids.dto.RecensioneDto;
import it.unicam.cs.ids.model.Recensione;
import it.unicam.cs.ids.services.RecensioneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecensioneController {

    @Autowired
    private RecensioneService recensioneService;

    @GetMapping("/poi/{poiId}/recensioni")
    public ResponseEntity<?> getAllRecensioniByPOI(@PathVariable Long poiId) {
        try {
            List<Recensione> recensioni = recensioneService.getAllRecensioniByPOI(poiId);
            if (recensioni.isEmpty()) {
                return ResponseEntity.ok("Nessuna recensione trovata per questo POI.");
            } else {
                return ResponseEntity.ok(recensioni);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @GetMapping("/recensioni/{id}")
    public ResponseEntity<?> getRecensioneById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(recensioneService.read(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('TURISTA', 'CONTRIBUTOR', 'CURATORE', 'ANIMATORE')")
    @PostMapping("/api/recensioni")
    public ResponseEntity<?> create(@RequestBody @Valid RecensioneDto recensioneDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Si sono verificati errori di validazione: " + result.getAllErrors());
        }
        try {
            return ResponseEntity.ok(recensioneService.create(recensioneDto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('TURISTA', 'CONTRIBUTOR', 'CURATORE', 'ANIMATORE')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Recensione> updateRecensione(@PathVariable Long id,
                                                       @RequestParam String commento,
                                                       @RequestParam int voto) {
        Recensione updatedRecensione = recensioneService.update(id, commento, voto);
        return ResponseEntity.ok(updatedRecensione);
    }

    @PreAuthorize("hasAnyAuthority('TURISTA', 'CONTRIBUTOR', 'CURATORE', 'ANIMATORE')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRecensione(@PathVariable Long id) {
        recensioneService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
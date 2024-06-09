package it.unicam.cs.ids.controller;

import it.unicam.cs.ids.model.Recensione;
import it.unicam.cs.ids.services.RecensioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/api/recensioni")
    public ResponseEntity<?> create(@RequestBody Recensione recensione) {
        try {
            return ResponseEntity.ok(recensioneService.create(recensione));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Recensione> updateRecensione(@PathVariable Long id,
                                                       @RequestParam String commento,
                                                       @RequestParam int voto) {
        Recensione updatedRecensione = recensioneService.update(id, commento, voto);
        return ResponseEntity.ok(updatedRecensione);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRecensione(@PathVariable Long id) {
        recensioneService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
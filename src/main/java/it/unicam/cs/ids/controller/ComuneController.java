package it.unicam.cs.ids.controller;

import it.unicam.cs.ids.dto.ComuneDto;
import it.unicam.cs.ids.model.Comune;
import it.unicam.cs.ids.services.ComuneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comune")
public class ComuneController {

    @Autowired
    private ComuneService comuneService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllComuni() {
        List<Comune> comuni = comuneService.read();
        try {
            if (comuni.isEmpty()) {
                return ResponseEntity.ok("Nessun comune trovato nel database.");
            } else {
                return ResponseEntity.ok(comuni);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getComuneById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(comuneService.read(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createComune(@RequestBody @Valid ComuneDto comuneDto,
                                               BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Si sono verificati errori di validazione: " + result.getAllErrors());
        }

        try {
            return ResponseEntity.ok(comuneService.create(comuneDto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComune(@PathVariable Long id, @RequestBody @Valid ComuneDto comuneDto,
                                          BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Si sono verificati errori di validazione: " + result.getAllErrors());
        }

        try {
            return ResponseEntity.ok(comuneService.update(id, comuneDto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComune(@PathVariable Long id) {
        try {
            comuneService.delete(id);
            return ResponseEntity.ok("Comune eliminato con successo.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }
}

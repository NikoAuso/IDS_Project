package it.unicam.cs.ids.controller;

import it.unicam.cs.ids.dto.ComuneRegistrationDto;
import it.unicam.cs.ids.model.Comune;
import it.unicam.cs.ids.services.ComuneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comuni")
public class ComuneController {

    @Autowired
    private ComuneService comuneService;

    @GetMapping
    public List<Comune> getAllComuni() {
        return comuneService.getAllComuni();
    }

    @GetMapping("/search")
    public ResponseEntity<?> getComuneByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(comuneService.getComuneByName(name));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createComune(@RequestBody @Valid ComuneRegistrationDto comuneRegistrationDto,
                                               BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Si sono verificati errori di validazione: " + result.getAllErrors());
        }

        try {
            return ResponseEntity.ok(comuneService.create(comuneRegistrationDto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }
}

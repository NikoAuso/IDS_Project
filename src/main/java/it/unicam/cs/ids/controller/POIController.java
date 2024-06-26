package it.unicam.cs.ids.controller;

import it.unicam.cs.ids.dto.POIDto;
import it.unicam.cs.ids.model.POI.POI;
import it.unicam.cs.ids.services.POIService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class POIController {

    @Autowired
    private POIService poiService;

    @GetMapping("/comune/{comuneId}/poi")
    public ResponseEntity<?> getAllPOIsOfComune(@PathVariable Long comuneId) {
        try {
            List<POI> poiList = poiService.getAllPOIsOfComune(comuneId);
            if (poiList.isEmpty()) {
                return ResponseEntity.ok("Nessun POI trovato per questo comune.");
            } else {
                return ResponseEntity.ok(poiList);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @GetMapping("/poi/{poiId}")
    public ResponseEntity<?> getPOIById(@PathVariable Long poiId) {
        try {
            return ResponseEntity.ok(poiService.read(poiId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('CONTRIBUTOR', 'CURATORE')")
    @PostMapping("/api/poi")
    public ResponseEntity<?> createPOI(@RequestBody @Valid POIDto poiDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Si sono verificati errori di validazione: " + result.getAllErrors());
        }

        try {
            return ResponseEntity.ok(poiService.create(poiDto, poiDto.getTipoPOI(), poiDto.getComune()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('CURATORE')")
    @PutMapping("/api/poi/{id}")
    public ResponseEntity<?> updatePOI(@RequestBody @Valid POIDto poiDto, @PathVariable Long id, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Si sono verificati errori di validazione: " + result.getAllErrors());
        }

        try {
            return ResponseEntity.ok(poiService.update(id, poiDto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('CURATORE')")
    @DeleteMapping("/api/poi/{id}")
    public ResponseEntity<?> deletePOI(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(poiService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('CURATORE')")
    @PostMapping("/api/poi/{poiId}/validate")
    public ResponseEntity<?> validatePOI(@PathVariable Long poiId) {
        try {
            return ResponseEntity.ok(poiService.validate(poiId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }
}

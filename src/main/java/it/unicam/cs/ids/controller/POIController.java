package it.unicam.cs.ids.controller;

import it.unicam.cs.ids.dto.POIDto;
import it.unicam.cs.ids.model.POI.POI;
import it.unicam.cs.ids.services.POIService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/poi")
public class POIController {

    @Autowired
    private POIService poiService;

    @GetMapping("/comune")
    public ResponseEntity<?> getAllPOIsOfComune(@RequestParam Long comuneId) {
        try {
            List<POI> poiList = poiService.getAllPOIsOfComune(comuneId);
            if (poiList.isEmpty()) {
                return ResponseEntity.ok("Nessun POI trovato nel database.");
            } else {
                return ResponseEntity.ok(poiList);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getPOIById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(poiService.read(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PostMapping
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
}

package it.unicam.cs.ids.controller;

import it.unicam.cs.ids.dto.ContenutoDto;
import it.unicam.cs.ids.dto.RichiestaPubblicazioneDto;
import it.unicam.cs.ids.enumeration.PiattaformeSocial;
import it.unicam.cs.ids.model.POI.Contenuto;
import it.unicam.cs.ids.services.ContenutoService;
import it.unicam.cs.ids.services.SocialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContenutoController {

    @Autowired
    private ContenutoService contenutoService;

    @Autowired
    private SocialService socialMediaService;

    @GetMapping("/poi/{poiId}/contenuti")
    public ResponseEntity<?> getAllContenutiByPOI(@PathVariable Long poiId) {
        try {
            List<Contenuto> contenuti = contenutoService.getAllContenutiByPOI(poiId);
            if (contenuti.isEmpty()) {
                return ResponseEntity.ok("Nessun contenuto trovato nel database.");
            } else {
                return ResponseEntity.ok(contenuti);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @GetMapping("/contenuto/{id}")
    public ResponseEntity<?> getContenutoById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(contenutoService.read(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('CONTRIBUTOR', 'CURATORE')")
    @PostMapping("/api/contenuti")
    public ResponseEntity<?> createContenuto(@RequestBody @Valid ContenutoDto contenutoDto) {
        try {
            return ResponseEntity.ok(contenutoService.create(contenutoDto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('CONTRIBUTOR', 'CURATORE')")
    @PutMapping("/api/contenuti/{id}")
    public ResponseEntity<?> updateContenuto(@PathVariable Long id, @RequestBody ContenutoDto contenutoDto) {
        try {
            return ResponseEntity.ok(contenutoService.update(id, contenutoDto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('CURATORE')")
    @DeleteMapping("/api/contenuti/{id}")
    public ResponseEntity<?> deleteContenuto(@PathVariable Long id) {
        try {
            contenutoService.delete(id);
            return ResponseEntity.ok("Contenuto eliminato con successo.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('CURATORE')")
    @PostMapping("/api/contenuti/{id}/validate")
    public ResponseEntity<?> validateContenuto(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(contenutoService.validate(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('CURATORE')")
    @PutMapping("/api/contenuti/publish")
    public ResponseEntity<?> publishContenuto(@RequestBody RichiestaPubblicazioneDto richiestaPubblicazioneDto) {
        List<Contenuto> contenuti = richiestaPubblicazioneDto.getContenuti().stream()
                .map(contenutoService::read)
                .filter(Contenuto::isValidato)
                .toList();

        List<PiattaformeSocial> piattaformeSocials = richiestaPubblicazioneDto.getSocials()
                .stream()
                .map(PiattaformeSocial::valueOf)
                .toList();

        if (contenuti.isEmpty()) {
            return ResponseEntity.status(500).body("Si è verificato un errore: uno o più contenuti non esistono o non sono stati validati.");
        }

        try {
            return ResponseEntity.ok(socialMediaService.publishToSocialMedia(contenuti, piattaformeSocials));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }
}

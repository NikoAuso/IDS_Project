package it.unicam.cs.ids.controller;

import it.unicam.cs.ids.dto.ContenutoDto;
import it.unicam.cs.ids.dto.RichiestaPubblicazioneDto;
import it.unicam.cs.ids.model.POI.contenuto.Contenuto;
import it.unicam.cs.ids.services.ContenutoService;
import it.unicam.cs.ids.services.SocialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        List<Contenuto> contenuti = contenutoService.getAllContenutiByPOI(poiId);
        try {
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

    //TODO: implementare la sicurezza
    @PostMapping("/api/contenuti/create")
    public ResponseEntity<?> createContenuto(@RequestBody @Valid ContenutoDto contenutoDto) {
        Contenuto newContenuto = contenutoService.create(contenutoDto);
        return ResponseEntity.ok(newContenuto);
    }

    @PutMapping("/api/contenuti/update/{id}")
    public ResponseEntity<?> updateContenuto(@PathVariable Long id, @RequestBody ContenutoDto contenutoDto) {
        Contenuto updatedContenuto = contenutoService.update(id, contenutoDto);
        return ResponseEntity.ok(updatedContenuto);
    }

    @DeleteMapping("/api/contenuti/delete/{id}")
    public ResponseEntity<?> deleteContenuto(@PathVariable Long id) {
        contenutoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/contenuti/publish")
    public ResponseEntity<?> publishContenuto(@RequestBody RichiestaPubblicazioneDto richiestaPubblicazioneDto) {
        List<Contenuto> contenuti = richiestaPubblicazioneDto.getContenuti().stream()
                .map(contenutoService::read)
                .filter(Contenuto::isValidato)
                .toList();

        if (contenuti.contains(null)) {
            return ResponseEntity.status(500).body("Si è verificato un errore: uno o più contenuti non esistono o non sono stati validati.");
        }

        try {
            return ResponseEntity.ok("Contenuti pubblicati con successo: " +
                    socialMediaService.publishToSocialMedia(contenuti, richiestaPubblicazioneDto.getSocials()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }
}

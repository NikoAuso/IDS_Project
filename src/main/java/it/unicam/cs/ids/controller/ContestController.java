package it.unicam.cs.ids.controller;

import it.unicam.cs.ids.dto.ContenutoDto;
import it.unicam.cs.ids.dto.ContestDto;
import it.unicam.cs.ids.model.Contest;
import it.unicam.cs.ids.model.POI.contenuto.ContenutoContest;
import it.unicam.cs.ids.services.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContestController {

    @Autowired
    private ContestService contestService;

    @GetMapping("/contest/all")
    public ResponseEntity<?> getAllContests() {
        try {
            List<Contest> contests = contestService.getAllContests();
            if (contests.isEmpty()) {
                return ResponseEntity.ok("Nessun contest trovato nel database.");
            } else {
                return ResponseEntity.ok(contests);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @GetMapping("/contest/{contestId}")
    public ResponseEntity<?> getContestById(@PathVariable Long contestId) {
        try {
            return ResponseEntity.ok(contestService.read(contestId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @GetMapping("/contest/{contestId}/contenuti")
    public ResponseEntity<?> getAllContenutiByContest(@PathVariable Long contestId) {
        try {
            List<ContenutoContest> contests = contestService.getAllContenutiByContest(contestId);
            if (contests.isEmpty()) {
                return ResponseEntity.ok("Nessun contenuto trovato nel contest.");
            } else {
                return ResponseEntity.ok(contests);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('ANIMATORE')")
    @PostMapping("/api/contest/create")
    public ResponseEntity<?> createContest(@RequestBody ContestDto contestDto) {
        try {
            return ResponseEntity.ok(contestService.create(contestDto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('ANIMATORE')")
    @PutMapping("/api/contest/update/{id}")
    public ResponseEntity<?> updateContest(@PathVariable Long id, @RequestBody Contest contestDetails) {
        try {
            return ResponseEntity.ok(contestService.update(id, contestDetails));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('ANIMATORE')")
    @DeleteMapping("/api/contest/delete/{id}")
    public ResponseEntity<?> deleteContest(@PathVariable Long id) {
        try {
            contestService.delete(id);
            return ResponseEntity.ok("Contest eliminato con successo.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('CONTRIBUTOR', 'CURATORE')")
    @PostMapping("/api/contest/{contestId}/contenuti/create")
    public ResponseEntity<?> createContenutoContest(@PathVariable Long contestId, @RequestBody ContenutoDto contenutoDto) {
        try {
            return ResponseEntity.ok(contestService.createContenutoContest(contestId, contenutoDto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('CONTRIBUTOR', 'CURATORE')")
    @PostMapping("/api/contest/partecipa/{contestId}")
    public ResponseEntity<?> partecipaContest(@PathVariable Long contestId) {
        try {
            return ResponseEntity.ok(contestService.partecipa(contestId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('ANIMATORE')")
    @GetMapping("/api/contest/{contestId}/getLink")
    public ResponseEntity<?> getLink(@PathVariable Long contestId) {
        try {
            return ResponseEntity.ok(contestService.getLink(contestId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('ANIMATORE')")
    @PostMapping("/api/contest/{contestId}/contenuto/{contenutoId}/validate")
    public ResponseEntity<?> validateContest(@PathVariable Long contestId, @PathVariable Long contenutoId) {
        try {
            return ResponseEntity.ok(contestService.validate(contestId, contenutoId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('ANIMATORE')")
    @PostMapping("/api/contest/{contestId}/contenuto/{contenutoId}/denied")
    public ResponseEntity<?> denyContest(@PathVariable Long contestId, @PathVariable Long contenutoId, @RequestBody String motivo) {
        try {
            return ResponseEntity.ok(contestService.deny(contestId, contenutoId, motivo));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('ANIMATORE')")
    @PostMapping("/api/contest/{contestId}/sendInviti")
    public ResponseEntity<?> sendInviti(@PathVariable Long contestId, @RequestBody List<Long> utentiPartecipanti) {
        try {
            return ResponseEntity.ok(contestService.sendInvitiContest(contestId, utentiPartecipanti));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }
}
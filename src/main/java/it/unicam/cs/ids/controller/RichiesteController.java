package it.unicam.cs.ids.controller;

import it.unicam.cs.ids.dto.AvanzamentoRuoloDto;
import it.unicam.cs.ids.dto.EliminazioneContenutoDto;
import it.unicam.cs.ids.dto.ModificaContenutoDto;
import it.unicam.cs.ids.dto.SegnalazioneDto;
import it.unicam.cs.ids.enumeration.StatusRichieste;
import it.unicam.cs.ids.model.richieste.EliminazioneContenuto;
import it.unicam.cs.ids.model.richieste.ModificaContenuto;
import it.unicam.cs.ids.model.richieste.Richieste;
import it.unicam.cs.ids.services.RichiesteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RichiesteController {
    @Autowired
    private RichiesteService richiesteService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/richieste/avanzamento-ruolo")
    public ResponseEntity<?> getAllAvanzamentoRuoloRichieste() {
        try {
            List<? extends Richieste> richiesteList = richiesteService.readAvanzamentoRuolo();
            if (richiesteList.isEmpty()) {
                return ResponseEntity.ok("Nessuna richiesta di avanzamento ruolo trovata.");
            } else {
                return ResponseEntity.ok(richiesteList);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/richieste/accreditamento-curatore")
    public ResponseEntity<?> getAllAccreditamentoCuratoreRichieste() {
        try {
            List<? extends Richieste> richiesteList = richiesteService.readAccreditamentoCuratore();
            if (richiesteList.isEmpty()) {
                return ResponseEntity.ok("Nessuna richiesta di accreditamento curatore trovata.");
            } else {
                return ResponseEntity.ok(richiesteList);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('CURATORE')")
    @GetMapping("/richieste/segnalazione")
    public ResponseEntity<?> getAllSegnalazioneRichieste() {
        try {
            List<? extends Richieste> richiesteList = richiesteService.readSegnalazione();
            if (richiesteList.isEmpty()) {
                return ResponseEntity.ok("Nessuna richiesta di segnalazione trovata.");
            } else {
                return ResponseEntity.ok(richiesteList);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('TURISTA', 'CONTRIBUTOR')")
    @PostMapping("/api/richesta-avanzamento")
    public ResponseEntity<?> createRichiestaAvanzamento(@RequestBody @Valid AvanzamentoRuoloDto avanzamentoRuoloDto,
                                                        BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Si sono verificati errori di validazione: " + result.getAllErrors());
        }

        try {
            return ResponseEntity.ok(richiesteService.createAvanzamentoRuolo(avanzamentoRuoloDto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PostMapping("/api/segnala-contenuto")
    public ResponseEntity<?> createSegnalazione(@RequestBody @Valid SegnalazioneDto segnalazioneDto,
                                                BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Si sono verificati errori di validazione: " + result.getAllErrors());
        }

        try {
            return ResponseEntity.ok(richiesteService.createSegnalazione(segnalazioneDto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('CONTRIBUTOR')")
    @PostMapping("/api/richesta-modifica-contenuto")
    public ResponseEntity<?> createModificaContenuto(@RequestBody @Valid ModificaContenutoDto modificaContenutoDto,
                                                        BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Si sono verificati errori di validazione: " + result.getAllErrors());
        }

        try {
            return ResponseEntity.ok(richiesteService.createModificaContenuto(modificaContenutoDto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PostMapping(value = "/api/richesta-modifica-contenuto/{id}/validate")
    public ResponseEntity<?> updateModificaContenutoValida(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(richiesteService.updateModificaContenuto(id, StatusRichieste.APPROVED));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PostMapping(value = "/api/richesta-modifica-contenuto/{id}/refused")
    public ResponseEntity<?> updateModificaContenutoNonValida(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(richiesteService.updateModificaContenuto(id, StatusRichieste.REFUSED));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('CONTRIBUTOR')")
    @PostMapping("/api/richesta-elimina-contenuto")
    public ResponseEntity<?> createEliminazioneContenuto(@RequestBody @Valid EliminazioneContenutoDto eliminazioneContenutoDto,
                                                     BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Si sono verificati errori di validazione: " + result.getAllErrors());
        }

        try {
            return ResponseEntity.ok(richiesteService.createEliminazioneContenuto(eliminazioneContenutoDto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('CONTRIBUTOR')")
    @PostMapping("/api/richesta-elimina-contenuto/{id}/validate")
    public ResponseEntity<?> updateEliminazioneContenutoValida(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(richiesteService.updateEliminazioneContenuto(id, StatusRichieste.APPROVED));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('CONTRIBUTOR')")
    @PostMapping("/api/richesta-elimina-contenuto/{id}/validate")
    public ResponseEntity<?> updateEliminazioneContenutoNonValida(@PathVariable Long id, @RequestBody EliminazioneContenutoDto eliminazioneContenutoDto) {
        try {
            return ResponseEntity.ok(richiesteService.updateEliminazioneContenuto(id, StatusRichieste.REFUSED, eliminazioneContenutoDto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }
}

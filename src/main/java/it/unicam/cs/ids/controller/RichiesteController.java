package it.unicam.cs.ids.controller;

import it.unicam.cs.ids.dto.AvanzamentoRuoloDto;
import it.unicam.cs.ids.dto.EliminazioneContenutoDto;
import it.unicam.cs.ids.dto.ModificaContenutoDto;
import it.unicam.cs.ids.dto.SegnalazioneDto;
import it.unicam.cs.ids.enumeration.StatusRichieste;
import it.unicam.cs.ids.model.richieste.AvanzamentoRuolo;
import it.unicam.cs.ids.model.richieste.EliminazioneContenuto;
import it.unicam.cs.ids.model.richieste.ModificaContenuto;
import it.unicam.cs.ids.model.richieste.Richieste;
import it.unicam.cs.ids.services.RichiesteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class RichiesteController {
    @Autowired
    private RichiesteService richiesteService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/api/richieste/avanzamento-ruolo")
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
    @GetMapping("/api/richieste/accreditamento-curatore")
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
    @GetMapping("/api/richieste/segnalazione")
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

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/api/richesta-avanzamento/{id}/validate")
    public ResponseEntity<?> updateRichiestaAvanzamentoValida(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(richiesteService.validateAvanzamentoRuolo(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/api/richesta-avanzamento/{id}/refuse")
    public ResponseEntity<?> updateRichiestaAvanzamentoNonValida(@PathVariable Long id, @RequestBody String motivazione) {
        try {
            return ResponseEntity.ok(richiesteService.denyAvanzamentoRuolo(id, motivazione));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }


    // RICHIESTE DI SEGNALAZIONE CONTENUTO
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


    // RICHIESTE DI MODIFICA CONTENUTO
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

    @PreAuthorize("hasAnyAuthority('CURATORE')")
    @PostMapping("/api/richesta-modifica-contenuto/{id}/validate")
    public ResponseEntity<?> updateModificaContenutoValida(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(richiesteService.updateModificaContenuto(id, StatusRichieste.APPROVED));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('CURATORE')")
    @PostMapping("/api/richesta-modifica-contenuto/{id}/refuse")
    public ResponseEntity<?> updateModificaContenutoNonValida(@PathVariable Long id, @RequestBody @Valid ModificaContenutoDto modificaContenutoDto,
                                                              BindingResult result) {
        try {
            return ResponseEntity.ok(richiesteService.updateModificaContenuto(id, modificaContenutoDto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }


    // RICHIESTE DI ELIMINAZIONE CONTENUTO
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

    @PreAuthorize("hasAnyAuthority('CURATORE')")
    @PostMapping("/api/richesta-elimina-contenuto/{id}/validate")
    public ResponseEntity<?> updateEliminazioneContenutoValida(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(richiesteService.updateEliminazioneContenuto(id, StatusRichieste.APPROVED));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('CURATORE')")
    @PostMapping("/api/richesta-elimina-contenuto/{id}/refuse")
    public ResponseEntity<?> updateEliminazioneContenutoNonValida(@PathVariable Long id, @RequestBody EliminazioneContenutoDto eliminazioneContenutoDto) {
        try {
            return ResponseEntity.ok(richiesteService.updateEliminazioneContenuto(id, StatusRichieste.REFUSED, eliminazioneContenutoDto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Si è verificato un errore: " + e.getMessage());
        }
    }
}

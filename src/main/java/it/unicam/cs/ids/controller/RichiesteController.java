/*
package it.unicam.cs.ids.controller;

import it.unicam.cs.ids.model.Users;
import it.unicam.cs.ids.services.RichiesteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class RichiesteController {
    @Autowired
    private RichiesteService richiesteService;

    // Endpoint per gestire la richiesta di avanzamento di ruolo
    @PostMapping("/avanzamento-ruolo")
    public ResponseEntity<String> avanzamentoRuolo(@RequestParam Long richiestaId, @RequestParam Users users) {
        richiesteService.avanzamentoRuolo(richiestaId, users);
        return new ResponseEntity<>("Avanzamento di ruolo eseguito con successo", HttpStatus.OK);
    }

    // Endpoint per gestire la richiesta di pubblicazione sui social
    @PostMapping("/pubblicazione-social")
    public ResponseEntity<String> pubblicazioneSocial(@RequestParam Long richiestaId, @RequestParam String contenuto, @RequestParam List<String> social) {
        richiesteService.pubblicazioneSocial(richiestaId, contenuto, social);
        return new ResponseEntity<>("Pubblicazione sui social eseguita con successo", HttpStatus.OK);
    }

    // Endpoint per gestire la richiesta di segnalazione
    @PostMapping("/segnalazione")
    public ResponseEntity<String> segnalazione(@RequestParam Long richiestaId, @RequestParam String motivo) {
        richiesteService.segnalazione(richiestaId, motivo);
        return new ResponseEntity<>("Segnalazione eseguita con successo", HttpStatus.OK);
    }

}
*/

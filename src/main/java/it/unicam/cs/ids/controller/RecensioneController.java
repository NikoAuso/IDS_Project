package it.unicam.cs.ids.controller;

import it.unicam.cs.ids.model.Recensione;
import it.unicam.cs.ids.services.RecensioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recensioni")
public class RecensioneController {

    @Autowired
    private RecensioneService recensioneService;

    @PostMapping("/create")
    public ResponseEntity<Recensione> createRecensione(@RequestParam String commento,
                                                       @RequestParam int voto,
                                                       @RequestParam Long poiId,
                                                       @RequestParam Long autoreId) {
        Recensione newRecensione = recensioneService.create(commento, voto, poiId, autoreId);
        return ResponseEntity.ok(newRecensione);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recensione> getRecensioneById(@PathVariable Long id) {
        Recensione recensione = recensioneService.read(id);
        return ResponseEntity.ok(recensione);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Recensione> updateRecensione(@PathVariable Long id,
                                                       @RequestParam String commento,
                                                       @RequestParam int voto) {
        Recensione updatedRecensione = recensioneService.update(id, commento, voto);
        return ResponseEntity.ok(updatedRecensione);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRecensione(@PathVariable Long id) {
        recensioneService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/poi/{poiId}")
    public ResponseEntity<List<Recensione>> getAllRecensioniByPOI(@PathVariable Long poiId) {
        List<Recensione> recensioni = recensioneService.getAllRecensioniByPOI(poiId);
        return ResponseEntity.ok(recensioni);
    }
}
package it.unicam.cs.ids.controller;

import it.unicam.cs.ids.dto.ContenutoDto;
import it.unicam.cs.ids.model.POI.contenuto.Contenuto;
import it.unicam.cs.ids.services.ContenutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contenuti")
public class ContenutoController {

    @Autowired
    private ContenutoService contenutoService;

    @PostMapping("/create")
    public ResponseEntity<Contenuto> createContenuto(@RequestBody ContenutoDto contenutoDto) {
        Contenuto newContenuto = contenutoService.create(contenutoDto);
        return ResponseEntity.ok(newContenuto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contenuto> getContenutoById(@PathVariable Long id) {
        Contenuto contenuto = contenutoService.read(id);
        return ResponseEntity.ok(contenuto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Contenuto> updateContenuto(@PathVariable Long id, @RequestBody ContenutoDto contenutoDto) {
        Contenuto updatedContenuto = contenutoService.update(id, contenutoDto);
        return ResponseEntity.ok(updatedContenuto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContenuto(@PathVariable Long id) {
        contenutoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/poi/{poiId}")
    public ResponseEntity<List<Contenuto>> getAllContenutiByPOI(@PathVariable Long poiId) {
        List<Contenuto> contenuti = contenutoService.getAllContenutiByPOI(poiId);
        return ResponseEntity.ok(contenuti);
    }
}

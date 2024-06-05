package it.unicam.cs.ids.controller;

import it.unicam.cs.ids.dto.ContenutoDto;
import it.unicam.cs.ids.model.POI.contenuto.Contenuto;
import it.unicam.cs.ids.services.ContenutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contenuti")
public class ContenutoController {

    @Autowired
    private ContenutoService contenutoService;

    @PostMapping
    public Contenuto createContenuto(@RequestBody ContenutoDto contenutoDto) {
        return contenutoService.create(contenutoDto);
    }

    @GetMapping("/{id}")
    public Contenuto getContenutoById(@PathVariable Long id) {
        return contenutoService.read(id);
    }
}

package it.unicam.cs.ids.services;

import it.unicam.cs.ids.model.POI.POI;
import it.unicam.cs.ids.model.POI.contenuto.Contenuto;
import it.unicam.cs.ids.repository.ContenutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContenutoService {

    @Autowired
    private ContenutoRepository contenutoRepository;

    public Contenuto create(Contenuto contenuto) {
        if (contenutoRepository.findById(contenuto.getContenutoId()).isPresent()) {
            throw new RuntimeException("il contenuto esiste già.");
        }

        Contenuto contenuto1 = new Contenuto(
                contenuto.getNome(),
                contenuto.getDescrizione(),
                contenuto.getTipo(),
                contenuto.getPoi(),
                contenuto.getAutore()
        );

        return contenutoRepository.save(contenuto1);
    }

    public Contenuto read(Long id) {
        return contenutoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("contenuto non trovato."));
    }

    /*public void update(int id, Contenuto contenuto) {
        if (id >= 0 && id < contenutoList.size() && contenutoList.get(id).getContenuto_id() == id) {
            contenutoList.set(id, contenuto);
        } else {
            throw new IllegalArgumentException("Contenuto non trovato!");
        }
    }

    public void delete(int id) {
        contenutoList.removeIf(i -> i.getContenuto_id() == id);
    }*/

    public List<Contenuto> getAllContenutiByPOI(POI poi) {
        return contenutoRepository.findContenutoByPoi(poi);
    }
}
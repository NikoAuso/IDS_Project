package it.unicam.cs.ids.services;

import it.unicam.cs.ids.model.POI.POI;
import it.unicam.cs.ids.model.Recensione;
import it.unicam.cs.ids.repository.RecensioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecensioneService {

    @Autowired
    private RecensioneRepository recensioneRepository;

    @Autowired
    private POIService poiService;

    @Autowired
    private UserService userService;

    public Recensione create(Recensione recensione) {
        Recensione recensioneToCreate = new Recensione(
                recensione.getCommento(),
                recensione.getVoto(),
                recensione.getPoi(),
                recensione.getAutore()
        );
        return recensioneRepository.save(recensioneToCreate);
    }

    public Recensione read(Long id) {
        return recensioneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recensione non trovata."));
    }

    public Recensione update(Long id, String commento, int voto) {
        Recensione recensione = recensioneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recensione non trovata"));

        recensione.setCommento(commento);
        recensione.setVoto(voto);

        return recensioneRepository.save(recensione);
    }

    public void delete(Long id) {
        if (recensioneRepository.existsById(id)) {
            recensioneRepository.deleteById(id);
        } else {
            throw new RuntimeException("Recensione non trovata.");
        }
    }

    public List<Recensione> getAllRecensioniByPOI(Long poiId) {
        POI poi = poiService.read(poiId);
        return recensioneRepository.findRecensioniByPoi(poi);
    }
}

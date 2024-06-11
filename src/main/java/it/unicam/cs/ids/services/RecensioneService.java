package it.unicam.cs.ids.services;

import it.unicam.cs.ids.dto.RecensioneDto;
import it.unicam.cs.ids.model.POI.POI;
import it.unicam.cs.ids.model.Recensione;
import it.unicam.cs.ids.repository.POIRepository;
import it.unicam.cs.ids.repository.RecensioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecensioneService {

    @Autowired
    private RecensioneRepository recensioneRepository;

    @Autowired
    private POIRepository poiRepository;

    @Autowired
    private UserService userService;

    public Recensione create(RecensioneDto recensioneDto) {
        Recensione recensioneToCreate = new Recensione(
                recensioneDto.getCommento(),
                recensioneDto.getVoto(),
                poiRepository.findById(recensioneDto.getPoiId())
                        .orElseThrow(() -> new RuntimeException("POI non trovato.")),
                userService.read(recensioneDto.getAutoreId())
        );
        return recensioneRepository.save(recensioneToCreate);
    }

    public Recensione read(Long id) {
        return recensioneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("recensione non trovata."));
    }

    public Recensione update(Long id, String commento, int voto) {
        Recensione recensione = recensioneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("recensione non trovata"));

        recensione.setCommento(commento);
        recensione.setVoto(voto);

        return recensioneRepository.save(recensione);
    }

    public void delete(Long id) {
        if (recensioneRepository.existsById(id)) {
            recensioneRepository.deleteById(id);
        } else {
            throw new RuntimeException("recensione non trovata.");
        }
    }

    public List<Recensione> getAllRecensioniByPOI(Long poiId) {
        POI poi = poiRepository.findById(poiId)
                .orElseThrow(() -> new RuntimeException("POI non trovato."));
        return recensioneRepository.findRecensioniByPoi(poi);
    }
}

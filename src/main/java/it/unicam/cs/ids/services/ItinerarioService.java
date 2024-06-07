package it.unicam.cs.ids.services;

import it.unicam.cs.ids.dto.ItinerarioDto;
import it.unicam.cs.ids.model.Comune;
import it.unicam.cs.ids.model.Itinerario;
import it.unicam.cs.ids.model.POI.POI;
import it.unicam.cs.ids.model.Users;
import it.unicam.cs.ids.observer.ObserverImpl;
import it.unicam.cs.ids.observer.Publisher;
import it.unicam.cs.ids.repository.ItinerarioRepository;
import it.unicam.cs.ids.repository.POIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItinerarioService extends Publisher {

    @Autowired
    private ItinerarioRepository itinerarioRepository;

    @Autowired
    private POIRepository poiRepository;

    private final ObserverImpl observer = new ObserverImpl();

    public Itinerario create(ItinerarioDto itinerarioDto) {
        addObserver(observer);

        if (itinerarioRepository.findByNome(itinerarioDto.getNome()).isPresent()){
            throw new RuntimeException("l'itinerario esiste già.");
        }

        checkComunePOI(itinerarioDto);

        Itinerario itinerario = new Itinerario(
                itinerarioDto.getNome(),
                itinerarioDto.getDescrizione(),
                itinerarioDto.getDistanza(),
                itinerarioDto.getPercorso(),
                itinerarioDto.getMaterialiMultimediali(),
                itinerarioDto.getAutore()
        );

        Users user = itinerario.getPercorso().getFirst().getComune().getCuratore();
        notifyObservers(user, "Itinerario creato");

        removeObserver(observer);

        return itinerarioRepository.save(itinerario);
    }

    public Itinerario read(Long id) {
        return itinerarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("itinerario non trovato."));

    }

    public Itinerario update(Long id, ItinerarioDto itinerarioDto) {
        return itinerarioRepository.findById(id).map(c -> {
            c.setNome(itinerarioDto.getNome());
            c.setDescrizione(itinerarioDto.getDescrizione());
            c.setDistanza(itinerarioDto.getDistanza());
            c.setPercorso(itinerarioDto.getPercorso());
            c.setMaterialiMultimediali(itinerarioDto.getMaterialiMultimediali());
            c.setAutore(itinerarioDto.getAutore());
            return itinerarioRepository.save(c);
        }).orElseThrow(() -> new RuntimeException("itinerario non trovato."));
    }

    public void delete(Long id) {
        if (itinerarioRepository.existsById(id)) {
            itinerarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("itinerario non trovato.");
        }
    }

    public List<Itinerario> getAll() {
        return itinerarioRepository.findAll();
    }

    public Itinerario addPointOfInterest(Long itinerarioId, Long poiId) {
        return itinerarioRepository.findById(itinerarioId).map(i -> {
                    List<POI> percorso = i.getPercorso();
                    POI poi = poiRepository.findById(poiId)
                            .orElseThrow(()-> new RuntimeException("POI non trovato"));
                    percorso.add(poi);
                    i.setPercorso(percorso);
                    return itinerarioRepository.save(i);
        })
                .orElseThrow(() -> new RuntimeException("Itinerario non trovato!"));
    }

    public Itinerario removePointOfInterest(Long itinerarioId, Long poiId) {
        return itinerarioRepository.findById(itinerarioId).map(i -> {
                    List<POI> percorso = i.getPercorso();
                    POI poiToRemove = poiRepository.findById(poiId)
                            .orElseThrow(()-> new RuntimeException("POI non trovato"));
                    if(percorso.contains(poiToRemove)) {
                        percorso.remove(poiToRemove);
                        i.setPercorso(percorso);
                        return itinerarioRepository.save(i);
                    }else
                        throw new RuntimeException("POI non trovato nell'itinerario.");

                })
                .orElseThrow(() -> new RuntimeException("Itinerario non trovato!"));
    }

    private boolean checkComunePOI(ItinerarioDto itinerarioDto){
        Comune comune = itinerarioDto.getPercorso().getFirst().getComune();
        for (POI poi : itinerarioDto.getPercorso()) {
            if (poi.getComune() != comune)
                throw new RuntimeException("i POI che compongono l'itinerario devono appartenere alla stesso comune");
        }
        return true;
    }
}


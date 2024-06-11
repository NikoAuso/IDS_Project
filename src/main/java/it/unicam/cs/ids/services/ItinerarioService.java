package it.unicam.cs.ids.services;

import it.unicam.cs.ids.dto.ItinerarioDto;
import it.unicam.cs.ids.dto.MaterialeMultimedialeDto;
import it.unicam.cs.ids.model.Comune;
import it.unicam.cs.ids.model.Itinerario;
import it.unicam.cs.ids.model.MaterialeMultimediale;
import it.unicam.cs.ids.model.POI.POI;
import it.unicam.cs.ids.model.Users;
import it.unicam.cs.ids.observer.ObserverImpl;
import it.unicam.cs.ids.observer.Publisher;
import it.unicam.cs.ids.repository.ItinerarioRepository;
import it.unicam.cs.ids.repository.MaterialeMultimedialeRepository;
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

    @Autowired
    private MaterialeMultimedialeRepository materialeMultimedialeRepository;

    private final ObserverImpl observer = new ObserverImpl();
    @Autowired
    private UserService userService;

    public Itinerario create(ItinerarioDto itinerarioDto) {
        addObserver(observer);

        if (itinerarioRepository.findByNome(itinerarioDto.getNome()).isPresent()){
            throw new RuntimeException("l'itinerario esiste già.");
        }

        if (itinerarioDto.getPercorso().size() < 2)
            throw new RuntimeException("l'itinerario deve avere almeno due punti di interesse.");

        checkComunePOI(itinerarioDto);

        Itinerario itinerario = new Itinerario(
                itinerarioDto.getNome(),
                itinerarioDto.getDescrizione(),
                itinerarioDto.getDistanza(),
                itinerarioDto.getDurata(),
                itinerarioDto.getPercorso()
                        .stream()
                        .map(
                                p -> poiRepository.findById(p)
                                        .orElseThrow(() -> new RuntimeException("POI non trovato."))
                        ).toList(),
                userService.read(itinerarioDto.getAutore())
        );

        if (userService.getAuthenticatedUser().getAutorizzato()) {
            itinerario.setValidato(true);
        }

        Users user = itinerario.getPercorso().getFirst().getComune().getCuratore();
        notifyObservers(user, "Itinerario creato");

        removeObserver(observer);

        return itinerarioRepository.save(itinerario);
    }

    public Itinerario read(Long id) {
        return itinerarioRepository.findById(id)
                .filter(Itinerario::isValidato)
                .orElseThrow(() -> new RuntimeException("itinerario non trovato."));

    }

    public Itinerario update(Long id, ItinerarioDto itinerarioDto) {
        return itinerarioRepository.findById(id).map(c -> {
            c.setNome(itinerarioDto.getNome());
            c.setDescrizione(itinerarioDto.getDescrizione());
            c.setDistanza(itinerarioDto.getDistanza());
            c.setPercorso(itinerarioDto.getPercorso()
                    .stream()
                    .map(
                            p -> poiRepository.findById(p)
                                    .orElseThrow(() -> new RuntimeException("POI non trovato."))
                    ).toList());
            c.setAutore(userService.read(itinerarioDto.getAutore()));
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
        return itinerarioRepository.findAll()
                .stream().filter(Itinerario::isValidato).toList();
    }

    public Itinerario addPointOfInterest(Long itinerarioId, Long poiId) {
        return itinerarioRepository.findById(itinerarioId)
                .filter(Itinerario::isValidato)
                .map(i -> {
                    List<POI> percorso = i.getPercorso();
                    POI poi = poiRepository.findById(poiId)
                            .orElseThrow(() -> new RuntimeException("POI non trovato"));
                    percorso.add(poi);
                    i.setPercorso(percorso);
                    return itinerarioRepository.save(i);
                })
                .orElseThrow(() -> new RuntimeException("itinerario non trovato!"));
    }

    public Itinerario removePointOfInterest(Long itinerarioId, Long poiId) {
        return itinerarioRepository.findById(itinerarioId)
                .filter(Itinerario::isValidato)
                .map(i -> {
                    List<POI> percorso = i.getPercorso();
                    POI poiToRemove = poiRepository.findById(poiId)
                            .orElseThrow(() -> new RuntimeException("POI non trovato"));
            if (percorso.contains(poiToRemove)) {
                percorso.remove(poiToRemove);
                i.setPercorso(percorso);
                return itinerarioRepository.save(i);
            } else
                throw new RuntimeException("POI non trovato nell'itinerario.");
        }).orElseThrow(() -> new RuntimeException("itinerario non trovato"));
    }

    public Itinerario addMaterialeMultimediale(Long itinerarioId, MaterialeMultimedialeDto materialeMultimedialeDto) {
        return itinerarioRepository.findById(itinerarioId)
                .filter(Itinerario::isValidato)
                .map(i -> {
            MaterialeMultimediale materialeMultimediale = new MaterialeMultimediale(
                    materialeMultimedialeDto.getTipo(),
                    i,
                    materialeMultimedialeDto.getFiles()
            );
            materialeMultimedialeRepository.save(materialeMultimediale);
            List<MaterialeMultimediale> materialiMultimediali = i.getMaterialiMultimediali();
            materialiMultimediali.add(materialeMultimediale);
            i.setMaterialiMultimediali(materialiMultimediali);
            return itinerarioRepository.save(i);
        }).orElseThrow(() -> new RuntimeException("itinerario non trovato"));
    }

    public Itinerario validateItinerario(Long itinerarioId) {
        return itinerarioRepository.findById(itinerarioId)
                .filter(i -> !i.isValidato())
                .map(i -> {
                    i.setValidato(true);
                    return itinerarioRepository.save(i);
                })
                .orElseThrow(() -> new RuntimeException("itinerario non trovato."));
    }

    private void checkComunePOI(ItinerarioDto itinerarioDto) {
        List<POI> percorso = itinerarioDto.getPercorso().stream().map(
                p -> poiRepository.findById(p)
                        .orElseThrow(() -> new RuntimeException("POI non trovato."))
        ).toList();
        Comune comune = percorso.getFirst().getComune();
        for (POI poi : percorso) {
            if (poi.getComune() != comune)
                throw new RuntimeException("I POI che compongono l'itinerario devono appartenere alla stesso comune");
        }
    }
}


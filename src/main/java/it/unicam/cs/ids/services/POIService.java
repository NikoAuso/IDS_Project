package it.unicam.cs.ids.services;

import it.unicam.cs.ids.dto.POIDto;
import it.unicam.cs.ids.enumeration.Ruoli;
import it.unicam.cs.ids.enumeration.TipoCategoriePOIFisico;
import it.unicam.cs.ids.enumeration.TipoCategoriePOILogico;
import it.unicam.cs.ids.enumeration.TipoPOI;
import it.unicam.cs.ids.model.Comune;
import it.unicam.cs.ids.model.POI.POI;
import it.unicam.cs.ids.model.POI.POIFisico;
import it.unicam.cs.ids.model.POI.POILogico;
import it.unicam.cs.ids.model.Users;
import it.unicam.cs.ids.observer.ObserverImpl;
import it.unicam.cs.ids.observer.Publisher;
import it.unicam.cs.ids.repository.ComuneRepository;
import it.unicam.cs.ids.repository.POIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class POIService extends Publisher {

    @Autowired
    private POIRepository poiRepository;

    @Autowired
    private ComuneRepository comuneRepository;

    @Autowired
    private UserService userService;

    private final ObserverImpl observer = new ObserverImpl();

    public POI create(POIDto poi, TipoPOI tipoPOI, Long comuneId) {
        addObserver(observer);

        if (poiRepository.findByLatitudineAndLongitudine(poi.getLatitudine(), poi.getLongitudine()).isPresent()) {
            throw new RuntimeException("E' già presente un POI a queste coordinate.");
        }

        POI poiToSave = createPOI(poi, tipoPOI, comuneId);
        poiToSave.setValidato(userService.getAuthenticatedUser().getRuolo() == Ruoli.CURATORE);
        poiRepository.save(poiToSave);

        Users user = comuneRepository.findById(comuneId)
                .orElseThrow(() -> new RuntimeException("comune non trovato."))
                .getCuratore();
        notifyObservers(user, "POI creato");

        removeObserver(observer);

        return poiToSave;
    }

    public POI read(Long id) {
        return poiRepository.findById(id)
                .filter(POI::isValidato)
                .orElseThrow(() -> new RuntimeException("POI non trovato."));
    }

    public POI update(Long id, POIDto poiDto) {
        return poiRepository.findById(id).map(p -> {
            addObserver(observer);

            Comune comune = comuneRepository.findById(poiDto.getComune())
                    .orElseThrow(() -> new RuntimeException("Comune non trovato."));

            p.setNome(poiDto.getNome());
            p.setDescrizione(poiDto.getDescrizione());
            p.setComune(comune);
            p.setLatitudine(poiDto.getLatitudine());
            p.setLongitudine(poiDto.getLongitudine());

            poiRepository.save(p);

            Users user = comuneRepository.findById(poiDto.getComune())
                    .orElseThrow(() -> new RuntimeException("comune non trovato."))
                    .getCuratore();
            notifyObservers(user, "POI modificato");
            removeObserver(observer);

            return p;
        }).orElseThrow(() -> new RuntimeException("POI non trovato."));
    }

    public POI delete(Long id) {
        return poiRepository.findById(id).map(p -> {
                    addObserver(observer);

                    poiRepository.deleteById(id);

                    Users user = comuneRepository.findById(p.getComune().getComuneId())
                            .orElseThrow(() -> new RuntimeException("comune non trovato."))
                            .getCuratore();
                    notifyObservers(user, "POI eliminato");
                    removeObserver(observer);

                    return p;
                })
                .orElseThrow(() -> new RuntimeException("POI non trovato."));
    }

    public List<POI> getAllPOIsOfComune(Long comuneId) {
        return poiRepository.findAllByComune(comuneRepository.findById(comuneId)
                        .orElseThrow(() -> new RuntimeException("Comune non trovato.")))
                .stream()
                .filter(POI::isValidato)
                .toList();
    }

    public POI validate(Long poiId) {
        return poiRepository.findById(poiId)
                .filter(p -> !p.isValidato())
                .map(p -> {
                    p.setValidato(true);
                    addObserver(observer);
                    Users toNotify = p.getComune().getCuratore();
                    notifyObservers(toNotify, "POI validato");
                    removeObserver(observer);
                    return poiRepository.save(p);
                })
                .orElseThrow(() -> new RuntimeException("POI non trovato."));
    }

    private POI createPOI (POIDto poi, TipoPOI tipoPOI, Long comuneId) {
        Comune comune = comuneRepository.findById(comuneId)
                .orElseThrow(() -> new RuntimeException("Comune non trovato."));
        if(tipoPOI.equals(TipoPOI.FISICO)) {
            return new POIFisico(
                    poi.getNome(),
                    poi.getDescrizione(),
                    comune,
                    poi.getLongitudine(),
                    poi.getLatitudine(),
                    poi.getIndirizzo(),
                    poi.getOrariDiApertura(),
                    poi.getOrariDiChiusura(),
                    poi.getServiziDisponibili(),
                    poi.getSitoWeb(),
                    poi.getContatti(),
                    (poi.getCategoriaFisico() != null ? poi.getCategoriaFisico() : TipoCategoriePOIFisico.GENERICO),
                    userService.getAuthenticatedUser().getAutorizzato()
            );
        } else if(tipoPOI.equals(TipoPOI.LOGICO)) {
            return new POILogico(
                    poi.getNome(),
                    poi.getDescrizione(),
                    comune,
                    poi.getLongitudine(),
                    poi.getLatitudine(),
                    poi.getInformazioniStoriche(),
                    poi.getArea(),
                    (poi.getCategoriaLogico() != null ? poi.getCategoriaLogico() : TipoCategoriePOILogico.GENERICO),
                    userService.getAuthenticatedUser().getAutorizzato()
            );
        } else {
            throw new RuntimeException("Tipo POI non valido.");
        }
    }
}

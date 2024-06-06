package it.unicam.cs.ids.services;

import it.unicam.cs.ids.dto.POIDto;
import it.unicam.cs.ids.enumeration.TipoCategorieFisico;
import it.unicam.cs.ids.enumeration.TipoCategorieLogico;
import it.unicam.cs.ids.enumeration.TipoPOI;
import it.unicam.cs.ids.model.Comune;
import it.unicam.cs.ids.model.POI.POI;
import it.unicam.cs.ids.model.POI.POIFisico;
import it.unicam.cs.ids.model.POI.POILogico;
import it.unicam.cs.ids.model.Users;
import it.unicam.cs.ids.observer.ObserverImpl;
import it.unicam.cs.ids.observer.Publisher;
import it.unicam.cs.ids.repository.POIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class POIService extends Publisher {

    @Autowired
    private POIRepository poiRepository;

    @Autowired
    private ComuneService comuneService;

    private final ObserverImpl observer = new ObserverImpl();

    public POI create(POIDto poi, TipoPOI tipoPOI, Long comuneId) {
        addObserver(observer);

        if (poiRepository.findByLatitudineAndLongitudine(poi.getLatitudine(), poi.getLongitudine()).isPresent()) {
            throw new RuntimeException("E' già presente un POI a queste coordinate.");
        }

        POI poiResult = poiRepository.save(createPOI(poi, tipoPOI, comuneId));

        Users user = comuneService.read(comuneId).getCuratore();
        notifyObservers(user, "POI creato");

        removeObserver(observer);

        return poiResult;
    }

    public POI read(Long id) {
        return poiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("POI non trovato."));
    }

    public POI update(Long id, POI poi) {
        addObserver(observer);

        POI poiToUpdate = poiRepository.findById(id).map(p -> {
            p.setNome(poi.getNome());
            p.setDescrizione(poi.getDescrizione());
            p.setComune(poi.getComune());
            p.setLatitudine(poi.getLatitudine());
            p.setLongitudine(poi.getLongitudine());
            return poiRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("POI non trovato."));

        Users user = comuneService.read(poi.getComune().getComuneId()).getCuratore();
        notifyObservers(user, "POI modificato");

        removeObserver(observer);

        return poiToUpdate;
    }

    public void delete(Long id) {
        poiRepository.findById(id).map(p -> {
                    addObserver(observer);

                    poiRepository.deleteById(id);

                    Users user = comuneService.read(p.getComune().getComuneId()).getCuratore();
                    notifyObservers(user, "POI eliminato");

                    removeObserver(observer);

                    return p;

                })
                .orElseThrow(() -> new RuntimeException("POI non trovato."));
    }

    public List<POI> getAllPOIsOfComune(Long comuneId) {
        Comune comune = comuneService.read(comuneId);
        return poiRepository.findAllByComune(comune);
    }

    private POI createPOI (POIDto poi, TipoPOI tipoPOI, Long comuneId) {
        Comune comune = comuneService.read(comuneId);
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
                    (poi.getCategoriaFisico() != null ? poi.getCategoriaFisico() : TipoCategorieFisico.GENERICO)
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
                    (poi.getCategoriaLogico() != null ? poi.getCategoriaLogico() : TipoCategorieLogico.GENERICO)
            );
        } else {
            throw new RuntimeException("Tipo POI non valido.");
        }
    }
}

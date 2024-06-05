package it.unicam.cs.ids.services;

import it.unicam.cs.ids.dto.POIDto;
import it.unicam.cs.ids.enumeration.TipoPOI;
import it.unicam.cs.ids.enumeration.TipoRuolo;
import it.unicam.cs.ids.model.Comune;
import it.unicam.cs.ids.model.POI.POI;
import it.unicam.cs.ids.model.POI.POIFisico;
import it.unicam.cs.ids.model.POI.POILogico;
import it.unicam.cs.ids.model.Users;
import it.unicam.cs.ids.observer.Publisher;
import it.unicam.cs.ids.repository.POIRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class POIService extends Publisher {

    @Autowired
    private POIRepository poiRepository;

    @Autowired
    private ComuneService comuneService;

    public POI create(POIDto poi, TipoPOI tipoPOI, Long comuneId) {
        if (poiRepository.findByLatitudineAndLongitudine(poi.getLatitudine(), poi.getLongitudine()).isPresent()) {
            throw new RuntimeException("E' già presente un POI a queste coordinate.");
        }

        POI poiResult = poiRepository.save(createPOI(poi, tipoPOI, comuneId));

        try {
            Users user = comuneService.read(comuneId).getCuratore();
            notifyObservers(user, "POI creato");
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }

        return poiResult;
    }

    public POI read(Long id) {
        return poiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("POI non trovato."));
    }

    public POI update(Long id, POI poi) {
        return poiRepository.save(poi);
    }

    public void delete(Long id) {
        if (poiRepository.existsById(id)){
            poiRepository.deleteById(id);
        } else {
            throw new RuntimeException("POI non trovato.");
        }
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
                    poi.getCategoriaFisico()
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
                    poi.getCategoriaLogico()
            );
        } else {
            throw new RuntimeException("Tipo POI non valido.");
        }
    }
}

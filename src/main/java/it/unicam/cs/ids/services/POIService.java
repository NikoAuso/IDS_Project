package it.unicam.cs.ids.services;

import it.unicam.cs.ids.enumeration.TipoPOI;
import it.unicam.cs.ids.exceptions.POIException;
import it.unicam.cs.ids.model.POI.POI;

import java.util.ArrayList;
import java.util.List;

public class POIService {
    private final List<POI> poiList = new ArrayList<>();

    public POI create(ArrayList<?> dati, TipoPOI type) throws POIException {
        POI poi = createPOI(dati, type);
        poiList.add(poi);
        return poi;
    }

    // Factory method
    private POI createPOI(ArrayList<?> dati, TipoPOI type) throws POIException {
        /*if (type.equals(TipoPOI.FISICO)) {
            return new POIFisico(dati);
        } else if (type.equals(TipoPOI.LOGICO)) {
            return new POILogico(dati);
        } else {
            throw new POIException("Tipo POI non supportato!");
        }*/
        return null;
    }

    /*public POI read(int id) {
        Optional<POI> poi = poiList.stream().filter(p -> p.getPoi_id() == id).findFirst();
        return poi.orElse(null);
    }

    public void update(int id, POI poi) throws POIException {
        int index = -1;
        for (int i = 0; i < poiList.size(); i++) {
            if (poiList.get(i).getPoi_id() == id) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            poiList.set(index, poi);
        } else {
            throw new POIException("POI non trovato!");
        }
    }

    public void delete(int id) {
        poiList.removeIf(p -> p.getPoi_id() == id);
    }

    public List<POI> getAllContenutiByPOIID(int id) {
        List<POI> result = new ArrayList<>();
        for (POI poi : poiList) {
            if (poi.getComune().getId() == id) {
                result.add(poi);
            }
        }
        return result;
    }*/
}

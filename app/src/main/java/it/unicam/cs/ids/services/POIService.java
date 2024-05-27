package it.unicam.cs.ids.services;

import it.unicam.cs.ids.model.POI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class POIService {
    private final List<POI> poiList = new ArrayList<>();

    public POI create(POI poi) {
        poiList.add(poi);
        return poi;
    }

    public POI read(int id) {
        Optional<POI> poi = poiList.stream().filter(p -> p.getId() == id).findFirst();
        return poi.orElse(null);
    }

    public void update(int id, POI poi) {
        if (id >= 0 && id < poiList.size() && poiList.get(id).getId() == id) {
            poiList.set(id, poi);
        } else {
            throw new IllegalArgumentException("POI non trovato!");
        }
    }

    public void delete(int id) {
        poiList.removeIf(p -> p.getId() == id);
    }

    public List<POI> getAllByComuneID(int id) {
        return poiList;
    }
}

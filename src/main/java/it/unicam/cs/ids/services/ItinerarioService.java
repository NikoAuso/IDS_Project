package it.unicam.cs.ids.services;

import it.unicam.cs.ids.model.Itinerario;
import it.unicam.cs.ids.model.POI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItinerarioService {
    private final List<Itinerario> itinerarioList = new ArrayList<>();

    public Itinerario create(Itinerario itinerario) {
        itinerarioList.add(itinerario);
        return itinerario;
    }

    public Itinerario read(int id) {
        Optional<Itinerario> Itinerario = itinerarioList.stream().filter(i -> i.getId() == id).findFirst();
        return Itinerario.orElse(null);
    }

    public void update(int id, Itinerario itinerario) {
        if (id >= 0 && id < itinerarioList.size() && itinerarioList.get(id).getId() == id) {
            itinerarioList.set(id, itinerario);
        } else {
            throw new IllegalArgumentException("Itinerario non trovato!");
        }
    }

    public void delete(int id) {
        itinerarioList.removeIf(i -> i.getId() == id);
    }

    public List<Itinerario> getAll() {
        return itinerarioList;
    }

    public void addPOIToItinerario(int id, POI poi) {
        Itinerario Itinerario = read(id);
        if (Itinerario != null) {
            Itinerario.addPointOfInterest(poi);
        }
    }

    public void removePOIFromItinerario(int id, POI poi) {
        Itinerario Itinerario = read(id);
        if (Itinerario != null) {
            Itinerario.removePointOfInterest(poi);
        }
    }
}

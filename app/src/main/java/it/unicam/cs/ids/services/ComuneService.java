package it.unicam.cs.ids.services;

import it.unicam.cs.ids.model.Comune;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComuneService {
    private final List<Comune> comuneList = new ArrayList<>();
    private final List<Comune> poiList = new ArrayList<>();

    public Comune create(Comune comune) {
        comuneList.add(comune);
        return comune;
    }

    public Comune read(int id) {
        Optional<Comune> comune = comuneList.stream().filter(p -> p.getId() == id).findFirst();
        return comune.orElse(null);
    }

    public void update(int id, Comune comune) {
        if (id >= 0 && id < comuneList.size() && comuneList.get(id).getId() == id) {
            comuneList.set(id, comune);
        } else {
            throw new IllegalArgumentException("Comune non trovato!");
        }
    }

    public void delete(int id) {
        comuneList.removeIf(p -> p.getId() == id);
    }

    public List<Comune> getAllByComuneID(int id) {
        return comuneList;
    }

    public List<Comune> getAllPOIByComuneID(int id) {
        return poiList;
    }
}
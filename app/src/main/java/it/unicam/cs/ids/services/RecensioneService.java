package it.unicam.cs.ids.services;

import it.unicam.cs.ids.model.Recensione;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RecensioneService {
    private final List<Recensione> recensioniList = new ArrayList<>();

    public Recensione create(Recensione recensione) {
        recensioniList.add(recensione);
        return recensione;
    }

    public Recensione read(int id) {
        Optional<Recensione> recensione = recensioniList.stream().filter(r -> r.getId() == id).findFirst();
        return recensione.orElse(null);
    }

    public void update(int id, Recensione recensione) {
        if (id >= 0 && id < recensioniList.size() && recensioniList.get(id).getId() == id) {
            recensioniList.set(id, recensione);
        } else {
            throw new IllegalArgumentException("Recensione non trovata!");
        }
    }

    public void delete(int id) {
        recensioniList.removeIf(r -> r.getId() == id);
    }

    public List<Recensione> getAll() {
        return recensioniList;
    }
}

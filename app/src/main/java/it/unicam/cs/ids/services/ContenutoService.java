package it.unicam.cs.ids.services;

import it.unicam.cs.ids.model.Contenuto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContenutoService {
    private final List<Contenuto> contenutoList = new ArrayList<>();

    public Contenuto create(Contenuto contenuto) {
        contenutoList.add(contenuto);
        return contenuto;
    }

    public Contenuto read(int id) {
        Optional<Contenuto> Contenuto = contenutoList.stream().filter(i -> i.getId() == id).findFirst();
        return Contenuto.orElse(null);
    }

    public void update(int id, Contenuto contenuto) {
        if (id >= 0 && id < contenutoList.size() && contenutoList.get(id).getId() == id) {
            contenutoList.set(id, contenuto);
        } else {
            throw new IllegalArgumentException("Contest non trovato!");
        }
    }

    public void delete(int id) {
        contenutoList.removeIf(i -> i.getId() == id);
    }

}
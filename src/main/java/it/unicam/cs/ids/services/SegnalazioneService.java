package it.unicam.cs.ids.services;

import it.unicam.cs.ids.model.Segnalazione;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SegnalazioneService {
    private final List<Segnalazione> segnalazioniList = new ArrayList<>();

    public Segnalazione create(Segnalazione segnalazione) {
        segnalazioniList.add(segnalazione);
        return segnalazione;
    }

    public Segnalazione read(int id) {
        Optional<Segnalazione> segnalazione = segnalazioniList.stream().filter(p -> p.getId() == id).findFirst();
        return segnalazione.orElse(null);
    }

    public void update(int id, Segnalazione segnalazione) {
        if (id >= 0 && id < segnalazioniList.size() && segnalazioniList.get(id).getId() == id) {
            segnalazioniList.set(id, segnalazione);
        } else {
            throw new IllegalArgumentException("Segnalazione non trovata!");
        }
    }

    public void delete(int id) {
        segnalazioniList.removeIf(p -> p.getId() == id);
    }

    public List<Segnalazione> getAllByContenutoID(int id) {
        return segnalazioniList;
    }
}


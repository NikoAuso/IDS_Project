package it.unicam.cs.ids.services;

import it.unicam.cs.ids.enumClasses.TipoRichiesta;
import it.unicam.cs.ids.model.AvanzamentoRuolo;
import it.unicam.cs.ids.model.PubblicazioneSocial;
import it.unicam.cs.ids.model.Richieste;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RichiesteService {
    private final List<Richieste> richiesteList = new ArrayList<>();

    public Richieste create(ArrayList<?> dati, TipoRichiesta type) {
        Richieste richiesta = createRichiesta(dati, type);
        richiesteList.add(richiesta);
        return richiesta;
    }

    public Richieste createRichiesta(ArrayList<?> dati, TipoRichiesta type) {
        if(type.equals(TipoRichiesta.AVANZAMENTO_RUOLO))
            return new AvanzamentoRuolo(dati);
        else if (type.equals(TipoRichiesta.PUBBLICAZIONE_SOCIAL))
            return new PubblicazioneSocial(dati);
        else
            throw new IllegalArgumentException("Tipo richiesta non valido");
    }

    public Richieste read(int id) {
        Optional<Richieste> richiesta = richiesteList.stream().filter(p -> p.getId() == id).findFirst();
        return richiesta.orElse(null);
    }

    public void update(int id, Richieste richiesta) {
        if (id >= 0 && id < richiesteList.size() && richiesteList.get(id).getId() == id) {
            richiesteList.set(id, richiesta);
        } else {
            throw new IllegalArgumentException("Comune non trovato!");
        }
    }

    public void delete(int id) {
        richiesteList.removeIf(p -> p.getId() == id);
    }
}

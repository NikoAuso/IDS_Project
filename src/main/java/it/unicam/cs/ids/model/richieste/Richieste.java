package it.unicam.cs.ids.model.richieste;

import it.unicam.cs.ids.model.Users;

public interface Richieste {
    int getId();
    String getStatoRichiesta();
    Users getFrom();
    Users getTo();
}

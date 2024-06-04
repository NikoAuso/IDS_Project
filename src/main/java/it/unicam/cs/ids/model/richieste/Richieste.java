package it.unicam.cs.ids.model.richieste;

import it.unicam.cs.ids.enumeration.StatusRichieste;
import it.unicam.cs.ids.enumeration.TipoRichiesta;
import it.unicam.cs.ids.model.Users;

import java.time.LocalDateTime;

public interface Richieste {
    Long getId();

    StatusRichieste getStatoRichiesta();

    Users getFrom();
    Users getTo();

    String getDettagli();

    LocalDateTime getData();

    TipoRichiesta getTipoRichiesta();
}

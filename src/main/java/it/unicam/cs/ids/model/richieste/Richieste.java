package it.unicam.cs.ids.model.richieste;

import it.unicam.cs.ids.enumeration.StatusRichieste;
import it.unicam.cs.ids.enumeration.TipoRichiesta;

import java.time.LocalDateTime;

public interface Richieste {
    Long getId();

    StatusRichieste getStatoRichiesta();

    TipoRichiesta getTipoRichiesta();

    String getMotivazione();

    LocalDateTime getData();
}

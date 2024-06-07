package it.unicam.cs.ids.model.richieste;

import it.unicam.cs.ids.enumeration.StatusRichieste;
import it.unicam.cs.ids.enumeration.TipoRichiesta;
import it.unicam.cs.ids.model.POI.contenuto.Contenuto;
import it.unicam.cs.ids.model.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Segnalazione implements Richieste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long segnalazioneId;

    @ManyToOne
    @JoinColumn(name = "contenutoId", nullable = false)
    private Contenuto contenuto;

    @Column(nullable = false)
    private String dettagli;

    private String motivazione;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Users autore;

    @Column(nullable = false)
    private StatusRichieste statoSegnalazione;

    @Column(nullable = false)
    private LocalDateTime data;

    @Override
    public Long getId() {
        return segnalazioneId;
    }

    @Override
    public StatusRichieste getStatoRichiesta() {
        return statoSegnalazione;
    }

    @Override
    public String motivazione() {
        return "";
    }

    @Override
    public Users getFrom() {
        return null;
    }

    @Override
    public Users getTo() {
        return null;
    }

    @Override
    public TipoRichiesta getTipoRichiesta() {
        return TipoRichiesta.SEGNALAZIONE_CONTENUTO;
    }

    @Override
    public void setContenuto(String contenuto) {
    }

    @Override
    public void setStato(String stato) {
    }
}

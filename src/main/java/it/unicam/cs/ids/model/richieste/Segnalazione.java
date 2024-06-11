package it.unicam.cs.ids.model.richieste;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.unicam.cs.ids.enumeration.StatusRichieste;
import it.unicam.cs.ids.enumeration.TipoRichiesta;
import it.unicam.cs.ids.model.POI.contenuto.Contenuto;
import it.unicam.cs.ids.model.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Segnalazione implements Richieste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long richiestaId;

    /**
     * Lo stato della richiesta di segnalazione
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusRichieste statoSegnalazione;

    /**
     * La motivazione dell'eventuale rifiuto della richiesta
     */
    private String motivazione;

    /**
     * Il contenuto segnalato
     */
    @ManyToOne
    @JoinColumn(name = "contenutoId", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "contenutoId")
    private Contenuto contenuto;

    /**
     * Il commento della segnalazione
     */
    @Column(nullable = false)
    private String commento;

    /**
     * L'autore della segnalazione
     */
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
    private Users autore;

    @CreationTimestamp
    @Column(updatable = false, name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    public Segnalazione(Contenuto contenuto, String commento, Users autore) {
        this.statoSegnalazione = StatusRichieste.PENDING;
        this.contenuto = contenuto;
        this.commento = commento;
        this.autore = autore;
    }

    @Override
    public Long getId() {
        return richiestaId;
    }

    @Override
    public StatusRichieste getStatoRichiesta() {
        return statoSegnalazione;
    }

    @Override
    public TipoRichiesta getTipoRichiesta() {
        return TipoRichiesta.SEGNALAZIONE_CONTENUTO;
    }

    @Override
    public LocalDateTime getData() {
        return createdAt;
    }
}

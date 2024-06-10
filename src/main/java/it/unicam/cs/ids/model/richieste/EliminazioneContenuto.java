package it.unicam.cs.ids.model.richieste;

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
public class EliminazioneContenuto implements Richieste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long richiestaId;

    /**
     * Lo stato della richiesta di modifica del contenuto
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusRichieste statoRichiesta;

    /**
     * La motivazione dell'eventuale rifiuto della richiesta
     */
    @Column(nullable = false)
    private String motivazione;

    /**
     * Il richiedente dell'accreditamento
     */
    @ManyToOne
    @JoinColumn(name = "richiedente", nullable = false)
    private Users richiedente;

    /**
     * Il contenuto da modificare
     */
    @ManyToOne
    @JoinColumn(name = "contenutoId", nullable = false)
    private Contenuto contenuto;

    /**
     * Il commento della richiesta di modifica
     */
    @Column(nullable = false)
    private String motivazioneEliminazione;

    @CreationTimestamp
    @Column(name = "createdAt", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    public EliminazioneContenuto(Users richiedente, Contenuto contenuto, String motivazioneEliminazione) {
        this.statoRichiesta = StatusRichieste.PENDING;
        this.motivazione = null;
        this.richiedente = richiedente;
        this.contenuto = contenuto;
        this.motivazioneEliminazione = motivazioneEliminazione;
    }

    @Override
    public Long getId() {
        return richiestaId;
    }

    @Override
    public StatusRichieste getStatoRichiesta() {
        return statoRichiesta;
    }

    @Override
    public TipoRichiesta getTipoRichiesta() {
        return TipoRichiesta.MODIFICA_CONTENUTO;
    }

    @Override
    public LocalDateTime getData() {
        return createdAt;
    }
}

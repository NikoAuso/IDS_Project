package it.unicam.cs.ids.model.richieste;

import it.unicam.cs.ids.enumeration.Ruoli;
import it.unicam.cs.ids.enumeration.StatusRichieste;
import it.unicam.cs.ids.enumeration.TipoRichiesta;
import it.unicam.cs.ids.model.Comune;
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
public class AvanzamentoRuolo implements Richieste{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long richiestaId;

    /**
     * Lo stato della richiesta di avanzamento
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusRichieste statoAvanzamento;

    /**
     * La motivazione dell'eventuale rifiuto della richiesta
     */
    private String motivazione;

    /**
     * Il richiedente dell'accreditamento
     */
    @ManyToOne
    @JoinColumn(name = "richiedente", nullable = false)
    private Users richiedente;

    /**
     * Il ruolo richiesto dall'utente richiedente
     */
    @Column(nullable = false)
    private Ruoli ruoloRichiesto;

    /**
     * Il commento della richiesta di accreditamento
     */
    @Column(nullable = false)
    private String commento;

    @CreationTimestamp
    @Column(name = "createdAt", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    public AvanzamentoRuolo(Users richiedente, Ruoli ruoloRichiesto, String commento) {
        this.richiedente = richiedente;
        this.ruoloRichiesto = ruoloRichiesto;
        this.commento = commento;
        this.statoAvanzamento = StatusRichieste.PENDING;
    }

    @Override
    public Long getId() {
        return richiestaId;
    }

    @Override
    public StatusRichieste getStatoRichiesta() {
        return statoAvanzamento;
    }

    @Override
    public TipoRichiesta getTipoRichiesta() {
        return TipoRichiesta.AVANZAMENTO_RUOLO;
    }

    @Override
    public LocalDateTime getData() {
        return createdAt;
    }
}

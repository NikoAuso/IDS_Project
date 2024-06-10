package it.unicam.cs.ids.model.richieste;

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
public class AccreditamentoCuratore implements Richieste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long richiestaId;

    /**
     * Lo stato della richiesta di accreditamento
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusRichieste statoAccreditamento;

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
     * Il comune di cui si richiede l'accreditamento come curatore
     */
    @ManyToOne
    @JoinColumn(name = "comuneId", nullable = false)
    private Comune comune;

    /**
     * Il commento della richiesta di accreditamento
     */
    @Column(nullable = false)
    private String commento;

    @CreationTimestamp
    @Column(name = "createdAt", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    public AccreditamentoCuratore(Users richiedente, Comune comune, String commento) {
        this.statoAccreditamento = StatusRichieste.PENDING;
        this.motivazione = null;
        this.richiedente = richiedente;
        this.comune = comune;
        this.commento = commento;
    }

    @Override
    public Long getId() {
        return richiestaId;
    }

    @Override
    public StatusRichieste getStatoRichiesta() {
        return statoAccreditamento;
    }

    @Override
    public TipoRichiesta getTipoRichiesta() {
        return TipoRichiesta.ACCREDITAMENTO_CURATORE;
    }

    @Override
    public LocalDateTime getData() {
        return createdAt;
    }
}

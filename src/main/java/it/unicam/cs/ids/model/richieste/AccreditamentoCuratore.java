package it.unicam.cs.ids.model.richieste;

import it.unicam.cs.ids.enumeration.StatusRichieste;
import it.unicam.cs.ids.enumeration.TipoRichiesta;
import it.unicam.cs.ids.model.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccreditamentoCuratore implements Richieste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long richiestaId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusRichieste statoRichiesta;

    @ManyToOne
    @JoinColumn(name = "richiedente", nullable = false)
    private Users richiedente;

    @Column(nullable = false)
    private String commento;

    @CreationTimestamp
    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;


    @Override
    public Long getId() {
        return richiestaId;
    }

    @Override
    public StatusRichieste getStatoRichiesta() {
        return null;
    };

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
    public String getDettagli() {
        return "";
    }

    @Override
    public LocalDateTime getData() {
        return null;
    }

    @Override
    public TipoRichiesta getTipoRichiesta() {
        return null;
    }

    @Override
    public void setContenuto(String contenuto) {

    }

    @Override
    public void setStato(String stato) {

    }
}

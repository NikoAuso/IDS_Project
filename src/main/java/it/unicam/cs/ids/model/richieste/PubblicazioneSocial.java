package it.unicam.cs.ids.model.richieste;

import it.unicam.cs.ids.enumeration.StatusRichieste;
import it.unicam.cs.ids.enumeration.TipoRichiesta;
import it.unicam.cs.ids.model.Users;
import it.unicam.cs.ids.model.POI.contenuto.Contenuto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PubblicazioneSocial implements Richieste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long richiestaId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusRichieste statoRichiesta;

    @Column(nullable = false)
    private String titolo;

    @Column(nullable = false)
    private String descrizione;

    @Convert(converter = JsonListContenutoConverter.class)
    private List<Contenuto> contenuti;

    @Convert(converter = JsonListStringConverter.class)
    private List<String> social;

    @ManyToOne
    @JoinColumn(name = "autore", nullable = false)
    private Users curatore;

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
    public String motivazione() {
        return "";
    }

    @Override
    public Users getFrom() {
        return curatore;
    }

    @Override
    public Users getTo() {
        return null;
    }

    @Override
    public String getDettagli() {
        return this.toString();
    }

    @Override
    public LocalDateTime getData() {
        return createdAt;
    }

    @Override
    public TipoRichiesta getTipoRichiesta() {
        return TipoRichiesta.PUBBLICAZIONE_SOCIAL;
    }
}

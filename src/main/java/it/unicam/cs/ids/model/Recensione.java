package it.unicam.cs.ids.model;

import it.unicam.cs.ids.model.POI.POI;
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
public class Recensione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recensioneId;

    private String commento;

    private int voto;

    @ManyToOne
    @JoinColumn(name = "poi")
    private POI poi;

    @ManyToOne
    @JoinColumn(name = "autore")
    private Users autore;

    @CreationTimestamp
    @Column(updatable = false, name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    public Recensione(String commento, int voto, POI poi, Users autore) {
        this.commento = commento;
        this.voto = voto;
        this.poi = poi;
        this.autore = autore;
    }
}

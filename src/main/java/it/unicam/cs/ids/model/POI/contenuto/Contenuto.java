package it.unicam.cs.ids.model.POI.contenuto;

import it.unicam.cs.ids.enumeration.TipoContenuto;
import it.unicam.cs.ids.model.Contest;
import it.unicam.cs.ids.model.POI.POI;
import it.unicam.cs.ids.model.Users;
import it.unicam.cs.ids.model.richieste.Segnalazione;
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoContenuto")
public class Contenuto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contenutoId;

    @ManyToOne
    @JoinColumn(name = "poi", nullable = false)
    private POI poi;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoContenuto tipo;

    @Column(nullable = false)
    private boolean validato;

    @ManyToOne
    @JoinColumn(name = "contest", nullable = false)
    private Contest contest;

    @ManyToOne
    @JoinColumn(name = "autore", nullable = false)
    private Users autore;

    @OneToMany(mappedBy = "segnalazioneId", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Segnalazione> segnalazioni;

    @CreationTimestamp
    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    public Contenuto(String nome, String descrizione, TipoContenuto tipo, POI poi, Users autore) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipo = tipo;
        this.poi = poi;
        this.autore = autore;
        this.validato = false;
    }
}

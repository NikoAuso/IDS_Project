package it.unicam.cs.ids.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import it.unicam.cs.ids.model.POI.POI;
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
public class Itinerario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long itinerarioId;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false)
    private String distanza;

    @Column(nullable = false)
    private String durata;

    @Column(nullable = false)
    private boolean validato;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "poiItinerari",
            joinColumns = @JoinColumn(name = "itinerarioId"),
            inverseJoinColumns = @JoinColumn(name = "poiId")
    )
    @JsonIdentityReference
    private List<POI> percorso;

    @OneToMany(mappedBy = "materialeId", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIdentityReference
    private List<MaterialeMultimediale> materialiMultimediali;

    @ManyToOne
    @JoinColumn(name = "autore", nullable = false)
    private Users autore;

    @CreationTimestamp
    @Column(updatable = false, name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    public Itinerario(String nome, String descrizione, String distanza, String durata, List<POI> percorso, List<MaterialeMultimediale> materialiMultimediali, Users autore){
        this.nome = nome;
        this.descrizione = descrizione;
        this.distanza = distanza;
        this.durata = durata;
        this.percorso = percorso;
        this.materialiMultimediali = materialiMultimediali;
        this.autore = autore;
    }
}

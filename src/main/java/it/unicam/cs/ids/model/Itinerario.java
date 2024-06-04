package it.unicam.cs.ids.model;

import it.unicam.cs.ids.model.POI.POI;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "poiItinerari",
            joinColumns = @JoinColumn(name = "itinerarioId"),
            inverseJoinColumns = @JoinColumn(name = "poiId")
    )
    private List<POI> percorso;

    @OneToMany(mappedBy = "materialeId", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<MaterialeMultimediale> materialiMultimediali;

    @ManyToOne
    @JoinColumn(name = "autore", nullable = false)
    private Users autore;
}

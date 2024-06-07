package it.unicam.cs.ids.model.POI;

import it.unicam.cs.ids.enumeration.TipoPOI;
import it.unicam.cs.ids.model.Comune;
import it.unicam.cs.ids.model.Itinerario;
import it.unicam.cs.ids.model.POI.contenuto.Contenuto;
import it.unicam.cs.ids.model.Recensione;
import it.unicam.cs.ids.model.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoPoi", discriminatorType = DiscriminatorType.STRING)
public abstract class POI {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int poiId;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false)
    private double longitudine;

    @Column(nullable = false)
    private double latitudine;

    @Column(nullable = false)
    private boolean validato;

    /**
     * Comune in cui si trova il POI
     */
    @ManyToOne
    @JoinColumn(name = "comune", nullable = false)
    private Comune comune;

    /**
     * Lista degli utenti che hanno aggiunto il POI ai preferiti
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "preferiti",
            joinColumns = @JoinColumn(name = "poiId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private List<Users> users;

    /**
     * Lista dei contenuti associati al POI
     */
    @OneToMany(mappedBy = "contenutoId", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Contenuto> contenuti;

    /**
     * Lista degli itinerari che contengono il POI
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "poiItinerari",
            joinColumns = @JoinColumn(name = "poiId"),
            inverseJoinColumns = @JoinColumn(name = "itinerarioId")
    )
    private List<Itinerario> itinerari;

    @OneToMany(mappedBy = "recensioneId", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Recensione> recensioni;

    public POI() {
    }

    public POI(String nome, String descrizione, Comune comune, double longitudine, double latitudine) {
        this.nome = nome;
        this.descrizione = descrizione;

        this.comune = comune;

        this.longitudine = longitudine;
        this.latitudine = latitudine;
    }

    public abstract TipoPOI getType();
}

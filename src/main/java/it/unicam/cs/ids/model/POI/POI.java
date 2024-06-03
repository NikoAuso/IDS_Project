package it.unicam.cs.ids.model.POI;

import it.unicam.cs.ids.enumeration.TipoPOI;
import it.unicam.cs.ids.model.Comune;
import it.unicam.cs.ids.model.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_poi", discriminatorType = DiscriminatorType.STRING)
public abstract class POI {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int poi_id;

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

    @ManyToOne
    @JoinColumn(name = "comune", nullable = false)
    private Comune comune;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "preferiti_users",
            joinColumns = @JoinColumn(name = "poi_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Users> users;

    /*@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contenuto> contenuti;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recensione> recensioni;*/

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

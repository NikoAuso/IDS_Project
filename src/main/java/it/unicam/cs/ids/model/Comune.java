package it.unicam.cs.ids.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import it.unicam.cs.ids.model.POI.POI;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comuneId;

    @Column(nullable = false, unique = true)
    private String cap;

    private String nome;
    private String regione;
    private String provincia;

    @OneToOne
    @JoinColumn(name = "curatore")
    private Users curatore;

    @OneToMany(mappedBy = "poiId", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIdentityReference
    private List<POI> poiList;

    @CreationTimestamp
    @Column(updatable = false, name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Comune(String cap, String nome, String regione, String provincia, Users curatore) {
        this.cap = cap;
        this.nome = nome;
        this.regione = regione;
        this.provincia = provincia;
        this.curatore = curatore;
    }
}

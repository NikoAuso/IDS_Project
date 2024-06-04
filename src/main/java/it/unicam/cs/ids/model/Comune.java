package it.unicam.cs.ids.model;

import it.unicam.cs.ids.model.POI.POI;
import it.unicam.cs.ids.repository.UserRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

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
    private List<POI> poi;

    @CreationTimestamp
    @Column(updatable = false, name = "createdAt")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private Date updatedAt;

    public Comune(String cap, String nome, String regione, String provincia, Users curatore) {
        this.cap = cap;
        this.nome = nome;
        this.regione = regione;
        this.provincia = provincia;
        this.curatore = curatore;
    }
}

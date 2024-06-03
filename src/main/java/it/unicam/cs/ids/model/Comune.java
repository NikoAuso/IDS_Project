package it.unicam.cs.ids.model;

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
    private Long id;

    @Column(nullable = false, unique = true)
    private String cap;
    private String nome;
    private String regione;
    private String provincia;

    @OneToOne
    @JoinColumn(name = "curatore")
    private Users curatore;

    @OneToMany(mappedBy = "poi_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<POI> poi;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    public Comune(String cap, String nome, String regione, String provincia) {
        this.cap = cap;
        this.nome = nome;
        this.regione = regione;
        this.provincia = provincia;
    }
}

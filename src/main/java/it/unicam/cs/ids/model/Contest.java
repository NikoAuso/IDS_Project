package it.unicam.cs.ids.model;

import it.unicam.cs.ids.model.POI.contenuto.Contenuto;
import it.unicam.cs.ids.model.POI.contenuto.ContenutoContest;
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
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contestId;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false)
    private String tema;

    @Column(nullable = false)
    private Boolean aperto;

    @ManyToOne
    @JoinColumn(name = "animatore")
    private Users animatore;

    private String contenuti;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "partecipantiContest",
            joinColumns = @JoinColumn(name = "contestId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private List<Users> utenti;


    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;

    @CreationTimestamp
    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
}
package it.unicam.cs.ids.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIdentityReference
    private List<ContenutoContest> contenuti;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "partecipantiContest",
            joinColumns = @JoinColumn(name = "contestId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    @JsonIdentityReference
    private List<Users> utentiPartecipanti;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataInizio;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataFine;

    @CreationTimestamp
    @Column(name = "createdAt", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;
}
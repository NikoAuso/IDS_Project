package it.unicam.cs.ids.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
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
public class MaterialeMultimediale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materialeId;

    private String tipo;

    private boolean validato;

    /**
     * Itinerario a cui è associato il materiale multimediale
     */
    @ManyToOne
    @JoinColumn(name = "itinerario", nullable = false)
    @JsonIdentityReference
    private Itinerario itinerario;

    /**
     * Lista di file multimediali che sono stati caricati per il materiale multimediale
     */
    @ElementCollection
    @CollectionTable(name = "fileData", joinColumns = @JoinColumn(name = "materialeId"))
    @Column(name = "file")
    private List<byte[]> files;

    @CreationTimestamp
    @Column(updatable = false, name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}

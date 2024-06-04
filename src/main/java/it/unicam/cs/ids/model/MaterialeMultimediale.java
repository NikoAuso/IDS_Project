package it.unicam.cs.ids.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "itinerario", nullable = false)
    private Itinerario itinerario;

    @ElementCollection
    @CollectionTable(name = "fileData", joinColumns = @JoinColumn(name = "materialeId"))
    @Column(name = "file")
    private List<byte[]> files;
}

package it.unicam.cs.ids.model.POI.contenuto;

import it.unicam.cs.ids.model.Contest;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("CONTEST")
public class ContenutoContest extends Contenuto{
    @ManyToOne
    @JoinColumn(name = "contest", nullable = false)
    private Contest contest;

    private String motivazione;
}

package it.unicam.cs.ids.model.POI;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.unicam.cs.ids.model.Contest;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("CONTEST")
public class ContenutoContest extends Contenuto {
    @ManyToOne
    @JoinColumn(name = "contest")
    @JsonBackReference
    private Contest contest;

    private String motivazione;

    public ContenutoContest(Contenuto contenuto, Contest contest) {
        super(contenuto.getTitolo(), contenuto.getDescrizione(), contenuto.getUrl(), contenuto.getNote(), contenuto.getAutore(), contenuto.getTipo(), contenuto.getCategoria(), contenuto.getPoi());
        this.contest = contest;
    }
}

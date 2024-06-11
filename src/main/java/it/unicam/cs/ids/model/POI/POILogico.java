package it.unicam.cs.ids.model.POI;

import it.unicam.cs.ids.enumeration.TipoCategoriePOILogico;
import it.unicam.cs.ids.enumeration.TipoPOI;
import it.unicam.cs.ids.model.Comune;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("LOGICO")
public class POILogico extends POI{

    private String informazioniStoriche;
    private String area;

    @Enumerated(EnumType.STRING)
    private TipoCategoriePOILogico categoriaLogico;

    public POILogico(String nome, String descrizione, Comune comune, double longitudine, double latitudine,
                     String informazioniStoriche, String area, @NotNull TipoCategoriePOILogico categoria, boolean validato) {
        super(nome, descrizione, comune, longitudine, latitudine, validato);

        this.informazioniStoriche = informazioniStoriche;
        this.area = area;

        this.categoriaLogico = categoria;
    }

    @Override
    public TipoPOI getType() {
        return TipoPOI.LOGICO;
    }
}

package it.unicam.cs.ids.model.POI;

import it.unicam.cs.ids.enumeration.TipoCategoriePOILogico;
import it.unicam.cs.ids.enumeration.TipoPOI;
import it.unicam.cs.ids.model.Comune;
import jakarta.persistence.*;
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
    @Column(nullable = false)
    private TipoCategoriePOILogico categoria;

    public POILogico(String nome, String descrizione, Comune comune, double longitudine, double latitudine, String informazioniStoriche, String area, TipoCategoriePOILogico categoria) {
        super(nome, descrizione, comune, longitudine, latitudine);

        this.informazioniStoriche = informazioniStoriche;
        this.area = area;

        this.categoria = categoria;
    }

    @Override
    public TipoPOI getType() {
        return TipoPOI.LOGICO;
    }
}

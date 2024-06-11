package it.unicam.cs.ids.model.POI;

import it.unicam.cs.ids.enumeration.TipoCategoriePOIFisico;
import it.unicam.cs.ids.enumeration.TipoPOI;
import it.unicam.cs.ids.model.Comune;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("FISICO")
public class POIFisico extends POI {

    private String indirizzo;
    private LocalTime orariDiApertura;
    private LocalTime orariDiChiusura;
    private String serviziDisponibili;
    private String sitoWeb;
    private String contatti;

    @Enumerated(EnumType.STRING)
    private TipoCategoriePOIFisico categoriaFisico;

    public POIFisico(String nome, String descrizione, Comune comune, double longitudine, double latitudine,
                     String indirizzo, LocalTime orariDiApertura, LocalTime orariDiChiusura, String serviziDisponibili,
                     String sitoWeb, String contatti, @NotNull TipoCategoriePOIFisico categoria, boolean validato) {
        super(nome, descrizione, comune, longitudine, latitudine, validato);

        this.indirizzo = indirizzo;
        this.orariDiApertura = orariDiApertura;
        this.orariDiChiusura = orariDiChiusura;
        this.serviziDisponibili = serviziDisponibili;
        this.sitoWeb = sitoWeb;
        this.contatti = contatti;

        this.categoriaFisico = categoria;
    }

    @Override
    public TipoPOI getType() {
        return TipoPOI.FISICO;
    }
}

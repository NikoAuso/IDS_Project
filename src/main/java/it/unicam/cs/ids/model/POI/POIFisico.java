package it.unicam.cs.ids.model.POI;

import it.unicam.cs.ids.enumeration.TipoCategorieFisico;
import it.unicam.cs.ids.enumeration.TipoPOI;
import it.unicam.cs.ids.model.Comune;
import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.sql.Time;
import java.time.LocalTime;
import java.time.OffsetTime;

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
    @Column(nullable = false)
    private TipoCategorieFisico categoria;

    public POIFisico(String nome, String descrizione, Comune comune, double longitudine, double latitudine, String indirizzo, LocalTime orariDiApertura, LocalTime orariDiChiusura, String serviziDisponibili, String sitoWeb, String contatti, @NotNull TipoCategorieFisico categoria) {
        super(nome, descrizione, comune, longitudine, latitudine);

        this.indirizzo = indirizzo;
        this.orariDiApertura = orariDiApertura;
        this.orariDiChiusura = orariDiChiusura;
        this.serviziDisponibili = serviziDisponibili;
        this.sitoWeb = sitoWeb;
        this.contatti = contatti;

        this.categoria = TipoCategorieFisico.valueOf(categoria.toString());
    }

    @Override
    public TipoPOI getType() {
        return TipoPOI.FISICO;
    }
}

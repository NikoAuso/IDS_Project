package it.unicam.cs.ids.dto;

import it.unicam.cs.ids.enumeration.TipoCategorieFisico;
import it.unicam.cs.ids.enumeration.TipoCategorieLogico;
import it.unicam.cs.ids.enumeration.TipoPOI;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalTime;

@Data
public class POIDto {
    @NotEmpty(message = "Il nome è richiesto")
    private String nome;

    @NotEmpty(message = "La descrizione è richiesta")
    private String descrizione;

    @NotEmpty(message = "La longitudine è richiesta")
    private double longitudine;

    @NotEmpty(message = "La latitudine è richiesta")
    private double latitudine;

    @NotEmpty(message = "Il comune è richiesto")
    private Long comune;

    @NotEmpty(message = "Il tipo di POI è richiesto")
    private TipoPOI tipoPOI;

    // Campi specifici per i POI fisici
    private String indirizzo;
    private LocalTime orariDiApertura;
    private LocalTime orariDiChiusura;
    private String serviziDisponibili;
    private String sitoWeb;
    private String contatti;
    private TipoCategorieFisico categoriaFisico;

    // Campi specifici per i POI logici
    private String informazioniStoriche;
    private String area;
    private TipoCategorieLogico categoriaLogico;
}

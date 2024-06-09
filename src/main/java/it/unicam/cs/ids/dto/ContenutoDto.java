package it.unicam.cs.ids.dto;

import it.unicam.cs.ids.enumeration.TipoCategoriaContenuto;
import it.unicam.cs.ids.enumeration.TipoContenuto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContenutoDto {
    @NotNull(message = "Il campo poi non può essere vuoto.")
    private Long poiId;

    @NotEmpty(message = "Il campo tipo non può essere vuoto.")
    private TipoContenuto tipo;

    @NotEmpty(message = "Il campo tipo non può essere vuoto.")
    private TipoCategoriaContenuto categoria;

    @NotEmpty(message = "Il campo autore non può essere vuoto.")
    private Long autoreId;

    @NotEmpty(message = "Il campo titolo non può essere vuoto.")
    private String titolo;

    @NotEmpty(message = "Il campo descrizione non può essere vuoto.")
    private String descrizione;

    private String url;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private String note;
}

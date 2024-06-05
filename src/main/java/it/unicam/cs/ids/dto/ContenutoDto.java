package it.unicam.cs.ids.dto;

import it.unicam.cs.ids.enumeration.TipoContenuto;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContenutoDto {
    @NotEmpty(message = "Il campo poiId non può essere vuoto.")
    private Long poiId;

    @NotEmpty(message = "Il campo tipo non può essere vuoto.")
    private TipoContenuto tipo;

    @NotEmpty(message = "Il campo autoreId non può essere vuoto.")
    private Long autoreId;

    @NotEmpty(message = "Il campo validato non può essere vuoto.")
    private boolean validato;

    @NotEmpty(message = "Il campo titolo non può essere vuoto.")
    private String titolo;

    @NotEmpty(message = "Il campo descrizione non può essere vuoto.")
    private String descrizione;

    private String url;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private String note;
}

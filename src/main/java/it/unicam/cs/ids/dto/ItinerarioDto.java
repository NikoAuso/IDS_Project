package it.unicam.cs.ids.dto;

import it.unicam.cs.ids.model.MaterialeMultimediale;
import it.unicam.cs.ids.model.POI.POI;
import it.unicam.cs.ids.model.Users;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItinerarioDto {
    @NotEmpty(message = "Il nome è richiesto")
    private String nome;

    @NotEmpty(message = "La descrizione è richiesta")
    private String descrizione;

    @NotEmpty(message = "La distanza è richiesta")
    private String distanza;

    @NotEmpty(message = "La durata è richiesta")
    private String durata;

    @NotNull(message = "Il percorso è richiesto")
    private List<Long> percorso;

    @NotNull(message = "L'autore è richiesto")
    private Long autore;
}
package it.unicam.cs.ids.dto;

import it.unicam.cs.ids.enumeration.StatusRichieste;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SegnalazioneDto {
    private StatusRichieste statoSegnalazione;
    private String motivazione;

    @NotNull(message = "Il contenuto è richiesto")
    private Long contenutoId;

    @NotEmpty(message = "Il commento è richiesto")
    public String commento;
}

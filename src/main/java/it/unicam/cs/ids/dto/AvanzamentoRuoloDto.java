package it.unicam.cs.ids.dto;

import it.unicam.cs.ids.enumeration.Ruoli;
import it.unicam.cs.ids.enumeration.StatusRichieste;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvanzamentoRuoloDto {
    private StatusRichieste statoAvanzamento;
    private String motivazione;

    @NotEmpty(message = "Il richiedente è richiesto")
    public String ruoloRichiesto;

    @NotEmpty(message = "Il commento è richiesto")
    public String commento;
}

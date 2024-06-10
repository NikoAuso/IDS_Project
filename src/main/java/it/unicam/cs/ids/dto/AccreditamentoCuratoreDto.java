package it.unicam.cs.ids.dto;

import it.unicam.cs.ids.enumeration.StatusRichieste;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccreditamentoCuratoreDto {
    private StatusRichieste statoAccreditamento;
    private String motivazione;

    @NotEmpty(message = "Il comune è richiesto")
    public Long comuneId;

    @NotEmpty(message = "Il commento è richiesto")
    public String commento;
}

package it.unicam.cs.ids.dto;

import it.unicam.cs.ids.enumeration.StatusRichieste;
import it.unicam.cs.ids.model.POI.contenuto.Contenuto;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EliminazioneContenutoDto {
    private StatusRichieste statoRichiesta;
    private String motivazione;

    @NotEmpty(message = "Il richiedente è richiesto")
    public Long richiedente;

    @NotEmpty(message = "Il contenuto è richiesto")
    public Contenuto contenuto;

    @NotEmpty(message = "La descrizione della modifica è richiesto")
    public String motivazioneEliminazione;
}

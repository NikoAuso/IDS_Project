package it.unicam.cs.ids.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecensioneDto {
    @NotEmpty(message = "Il commento è richiesto")
    private String commento;

    @NotNull(message = "Il voto è richiesto")
    private int voto;

    @NotNull(message = "Il poi è richiesto")
    private Long poiId;

    @NotNull(message = "Il autore è richiesto")
    private Long autoreId;
}

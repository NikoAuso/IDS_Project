package it.unicam.cs.ids.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialeMultimedialeDto {
    private String tipo;

    @NotEmpty(message = "L'itinerario è richiesto")
    private Long itinerarioId;

    @NotEmpty(message = "Deve essere presente almeno un file")
    private String[] files;
}

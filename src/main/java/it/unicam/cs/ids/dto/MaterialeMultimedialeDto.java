package it.unicam.cs.ids.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialeMultimedialeDto {
    private String tipo;

    @NotNull(message = "L'itinerario è richiesto")
    private Long itinerarioId;

    @NotNull(message = "Deve essere presente almeno un file")
    private String[] files;
}

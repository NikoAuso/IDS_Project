package it.unicam.cs.ids.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuoloUpdateDto {
    @NotEmpty(message = "Il ruolo è richiesto")
    private String ruolo;
}

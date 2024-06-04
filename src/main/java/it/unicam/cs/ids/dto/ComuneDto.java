package it.unicam.cs.ids.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ComuneDto {
    @NotEmpty(message = "Il CAP è richiesto")
    @Size(min = 5, max = 5, message = "Il CAP deve avere 5 caratteri")
    private String cap;

    @NotEmpty(message = "Il nome è richiesto")
    private String nome;

    @NotEmpty(message = "La regione è richiesta")
    private String regione;

    @NotEmpty(message = "La provincia è richiesta")
    private String provincia;

    @Digits(integer = 10, fraction = 0, message = "E' richiesto l'ID del curatore")
    private Long curatore;
}

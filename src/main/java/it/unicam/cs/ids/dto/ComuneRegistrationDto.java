package it.unicam.cs.ids.dto;

import it.unicam.cs.ids.model.Users;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ComuneRegistrationDto {
    @NotEmpty(message = "Il CAP è richiesto")
    @Size(min = 5, max = 5, message = "Il CAP deve avere 5 caratteri")
    private String cap;

    @NotEmpty(message = "Il nome è richiesto")
    private String nome;

    @NotEmpty(message = "La regione è richiesta")
    private String regione;

    @NotEmpty(message = "La provincia è richiesta")
    private String provincia;

    @NotEmpty(message = "Il curatore è richiesto")
    private Users curatore;
}

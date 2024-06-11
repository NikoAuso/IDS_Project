package it.unicam.cs.ids.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewUserDto {

    @NotEmpty(message = "Il nome è richiesto")
    private String nome;

    @NotEmpty(message = "Il cognome è richiesto")
    private String cognome;

    @NotEmpty(message = "L'email è richiesta")
    @Email(message = "L'email non è valida")
    private String email;

    @NotEmpty(message = "Lo username è richiesto")
    @Size(min = 4, max = 20, message = "Lo username deve avere una lunghezza compresa tra 4 e 20 caratteri")
    private String username;

    @NotEmpty(message = "La password è richiesta")
    @Size(min = 6, message = "La password deve avere almeno 6 caratteri")
    private String password;

    @NotEmpty
    private boolean autorizzato;

    @NotEmpty
    private String ruolo;
}

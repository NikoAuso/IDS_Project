package it.unicam.cs.ids.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContestDto {
    @NotEmpty(message = "Il campo nome è richiesto")
    private String nome;

    @NotEmpty(message = "Il campo descrizione è richiesto")
    private String descrizione;

    @NotEmpty(message = "Il campo tema è richiesto")
    private String tema;

    @NotEmpty(message = "Il campo aperto è richiesto")
    private Boolean aperto;

    private Long[] utentiPartecipanti;

    @NotEmpty(message = "Il campo data di inizio è richiesto")
    private LocalDateTime dataInizio;

    @NotEmpty(message = "Il campo data di fine è richiesto")
    private LocalDateTime dataFine;
}

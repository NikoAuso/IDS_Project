package it.unicam.cs.ids.model.POI.contenuto;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("TEMPORIZZATO")
public class ContenutoTemporizzato extends Contenuto {
    private LocalDateTime inizio;
    private LocalDateTime fine;
}
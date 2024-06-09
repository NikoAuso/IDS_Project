package it.unicam.cs.ids.dto;

import it.unicam.cs.ids.enumeration.PiattaformeSocial;
import lombok.Data;

import java.util.List;

@Data
public class RichiestaPubblicazioneDto {
    private List<Long> contenuti;
    private List<PiattaformeSocial> socials;
}

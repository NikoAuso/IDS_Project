package it.unicam.cs.ids.services;

import it.unicam.cs.ids.enumeration.PiattaformeSocial;
import it.unicam.cs.ids.model.POI.contenuto.Contenuto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialService {
    public String publishToSocialMedia(List<Contenuto> contenuti, List<PiattaformeSocial> socials) throws Exception {
        for (Contenuto contenuto : contenuti) {
            for (PiattaformeSocial platform : socials) {
                return switch (platform) {
                    case TWITTER -> String.format("Pubblicazione su Twitter: %s", contenuto.toString());
                    case FACEBOOK -> String.format("Pubblicazione su Facebook: %s", contenuto.toString());
                    case INSTAGRAM -> String.format("Pubblicazione su Instagram: %s", contenuto.toString());
                    default -> throw new RuntimeException("piattaforma sconosciuta -> {" + platform + "}");
                };
            }
        }
        return "Pubblicazione completata con successo!";
    }
}

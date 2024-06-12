package it.unicam.cs.ids.services;

import it.unicam.cs.ids.enumeration.PiattaformeSocial;
import it.unicam.cs.ids.model.POI.Contenuto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialService {
    public String publishToSocialMedia(List<Contenuto> contenuti, List<PiattaformeSocial> socials) {
        StringBuilder result = new StringBuilder();
        for (PiattaformeSocial platform : socials) {
            switch (platform) {
                case TWITTER ->
                        result.append(String.format("Pubblicazione su Twitter: %s\n", contenuti.stream().map(Contenuto::getTitolo).toList()));
                case FACEBOOK ->
                        result.append(String.format("Pubblicazione su Facebook: %s\n", contenuti.stream().map(Contenuto::getTitolo).toList()));
                case INSTAGRAM ->
                        result.append(String.format("Pubblicazione su Instagram: %s\n", contenuti.stream().map(Contenuto::getTitolo).toList()));
                default -> throw new RuntimeException("piattaforma sconosciuta -> {" + platform + "}\n");
            };
        }
        return result.toString();
    }
}

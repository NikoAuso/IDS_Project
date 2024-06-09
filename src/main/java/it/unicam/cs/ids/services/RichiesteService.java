/*
package it.unicam.cs.ids.services;

import it.unicam.cs.ids.enumeration.StatusRichieste;
import it.unicam.cs.ids.enumeration.TipoRichiesta;
import it.unicam.cs.ids.model.Users;
import it.unicam.cs.ids.model.richieste.Richieste;
import it.unicam.cs.ids.repository.RichiesteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RichiesteService {

    @Autowired
    private RichiesteRepository richiesteRepository;

    // Creazione di una nuova richiesta di pubblicazione
    @Transactional
    public Richieste create(String contenuto) {
        Richieste richiesta = new Richieste() {
            @Override
            public Long getId() {
                return 0L;
            }

            @Override
            public StatusRichieste getStatoRichiesta() {
                return null;
            }

            @Override
            public String motivazione() {
                return "";
            }

            @Override
            public Users getFrom() {
                return null;
            }

            @Override
            public Users getTo() {
                return null;
            }

            @Override
            public String getDettagli() {
                return "";
            }

            @Override
            public LocalDateTime getData() {
                return null;
            }

            @Override
            public TipoRichiesta getTipoRichiesta() {
                return null;
            }

            @Override
            public void setContenuto(String contenuto) {

            }

            @Override
            public void setStato(String stato) {

            }
        };
        return richiesteRepository.save(richiesta);
    }

    // Aggiornamento di una richiesta esistente
    @Transactional
    public Richieste update(Long id, String contenuto, String stato) {
        Optional<Richieste> richiestaOpt = richiesteRepository.findById(id);
        if (richiestaOpt.isPresent()) {
            Richieste richiesta = richiestaOpt.get();
            richiesta.setContenuto(contenuto);
            richiesta.setStato(stato);
            return richiesteRepository.save(richiesta);
        } else {
            throw new IllegalArgumentException("Richiesta non trovata!");
        }
    }

    // Eliminazione di una richiesta
    @Transactional
    public void delete(Long id) {
        if (richiesteRepository.existsById(id)) {
            richiesteRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Richiesta non trovata!");
        }
    }

    // Recupero di tutte le richieste
    @Transactional(readOnly = true)
    public List<Richieste> getAllRichieste() {
        return richiesteRepository.findAll();
    }

    // Recupero di una richiesta per ID
    @Transactional(readOnly = true)
    public Optional<Richieste> getRichiesteById(Long id) {
        return richiesteRepository.findById(id);
    }

    // Aggiornamento dello stato della richiesta
    @Transactional
    public Richieste updateStatoRichieste(Long id, String stato) {
        Optional<Richieste> richiestaOpt = richiesteRepository.findById(id);
        if (richiestaOpt.isPresent()) {
            Richieste richieste = richiestaOpt.get();
            richieste.setStato(stato);
            return richiesteRepository.save(richieste);
        } else {
            throw new IllegalArgumentException("Richiesta non trovata!");
        }
    }

    */
/*//*
/ Logica aggiuntiva per interagire con AvanzamentoRuolo, PubblicazioneSocialDto, e Segnalazione
    @Transactional
    public void avanzamentoRuolo(Long richiestaId, Users users) {
        AvanzamentoRuolo avanzamentoRuolo = new AvanzamentoRuolo();
        avanzamentoRuolo.setRichiestaId(richiestaId);
        avanzamentoRuolo.setRichiedente(users);
        avanzamentoRuolo.setStato("PENDING");
        avanzamentoRuoloRepository.save(avanzamentoRuolo);
    }

    @Transactional
    public void pubblicazioneSocial(Long richiestaId, String contenuto, List<String> social) {
        PubblicazioneSocialDto pubblicazioneSocial = new PubblicazioneSocialDto();
        pubblicazioneSocial.setRichiestaId(richiestaId);
        pubblicazioneSocial.setContenuto(contenuto);
        pubblicazioneSocial.setSocial(social);
        pubblicazioneSocialRepository.save(pubblicazioneSocial);
    }

    @Transactional
    public void segnalazione(Long segnalazioneId, String motivazione) {
        Segnalazione segnalazione = new Segnalazione();
        segnalazione.setSegnalazioneId(segnalazioneId);
        segnalazione.setMotivazione(motivazione);
        segnalazione.setStato("");
        segnalazioneRepository.save(segnalazione);
        }*//*


}

*/

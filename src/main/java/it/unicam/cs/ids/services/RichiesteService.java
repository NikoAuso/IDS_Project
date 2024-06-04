package it.unicam.cs.ids.services;

import it.unicam.cs.ids.repository.AvanzamentoRuoloRepository;
import it.unicam.cs.ids.repository.PubblicazioneSocialRepository;
import it.unicam.cs.ids.repository.SegnalazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RichiesteService {

    @Autowired
    private AvanzamentoRuoloRepository avanzamentoRuoloRepository;

    @Autowired
    private PubblicazioneSocialRepository pubblicazioneSocialRepository;

    @Autowired
    private SegnalazioneRepository segnalazioneRepository;
}

package it.unicam.cs.ids.services;

import it.unicam.cs.ids.dto.UserRegistrationDto;
import it.unicam.cs.ids.model.Recensione;
import it.unicam.cs.ids.model.Users;
import it.unicam.cs.ids.repository.RecensioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecensioneService {

    @Autowired
    private RecensioneRepository recensioneRepository;

    public Recensione create(Recensione recensione) {
        if (recensioneRepository.findById(recensione.getRecensioneId()).isPresent()) {
            throw new RuntimeException("la recensione esiste già.");
        }

        Recensione recensione1 = new Recensione(
                recensione.getCommento(),
                recensione.getVoto(),
                recensione.getPoi(),
                recensione.getAutore()
        );

        return recensioneRepository.save(recensione1);
    }

    public Recensione read(Long id) {
        return recensioneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("recensione non trovata."));
    }

    public Recensione update(Long id, Recensione recensioneUpdated) {
        return recensioneRepository.findById(id).map(recensione -> {
            //TODO: implementare l'aggiornamento
            return recensioneRepository.save(recensione);
        }).orElseThrow(() -> new RuntimeException("recensione non trovata."));
    }

    public void delete(Long id) {
        if (recensioneRepository.existsById(id)) {
            recensioneRepository.deleteById(id);
        } else {
            throw new RuntimeException("recensione non trovata.");
        }
    }
}

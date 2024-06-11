package it.unicam.cs.ids.services;

import it.unicam.cs.ids.dto.ComuneDto;
import it.unicam.cs.ids.enumeration.Ruoli;
import it.unicam.cs.ids.model.Comune;
import it.unicam.cs.ids.model.Users;
import it.unicam.cs.ids.repository.ComuneRepository;
import it.unicam.cs.ids.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComuneService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ComuneRepository comuneRepository;

    public Comune create(ComuneDto comuneDto) {
        if (comuneRepository.findComuneByCap(comuneDto.getCap()).isPresent()) {
            throw new RuntimeException("il comune esiste già.");
        }

        if (comuneRepository.findComuneByCuratore(
                userRepository.findById(comuneDto.getCuratore())
                        .orElseThrow(() -> new RuntimeException("utente non trovato")))
                .isPresent())
            throw new RuntimeException("l'utente è già curatore di un comune.");

        Comune comune = new Comune(
                comuneDto.getCap(),
                comuneDto.getNome(),
                comuneDto.getRegione(),
                comuneDto.getProvincia(),
                checkUserCuratore(comuneDto.getCuratore())
        );

        return comuneRepository.save(comune);
    }

    public Comune create(ComuneDto comuneDto, Users curatore) {
        if (comuneRepository.findComuneByCap(comuneDto.getCap()).isPresent()) {
            throw new RuntimeException("il comune esiste già.");
        }

        if (comuneRepository.findComuneByCuratore(
                        userRepository.findById(comuneDto.getCuratore())
                                .orElseThrow(() -> new RuntimeException("utente non trovato")))
                .isPresent())
            throw new RuntimeException("l'utente è già curatore di un comune.");

        Comune comune = new Comune(
                comuneDto.getCap(),
                comuneDto.getNome(),
                comuneDto.getRegione(),
                comuneDto.getProvincia(),
                curatore
        );

        return comuneRepository.save(comune);
    }

    public List<Comune> read() {
        return comuneRepository.findAll();
    }

    public Comune read(Long id) {
        return comuneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("comune non trovato."));
    }

    public Comune read(String nome) {
        return comuneRepository.findComuneByNome(nome)
                .orElseThrow(() -> new RuntimeException("comune non trovato."));
    }

    public Comune update(Long id, ComuneDto comuneDto) {
        return comuneRepository.findById(id).map(c -> {
            c.setCap(comuneDto.getCap());
            c.setNome(comuneDto.getNome());
            c.setRegione(comuneDto.getRegione());
            c.setProvincia(comuneDto.getProvincia());
            c.setCuratore(checkUserCuratore(comuneDto.getCuratore()));
            return comuneRepository.save(c);
        }).orElseThrow(() -> new RuntimeException("comune non trovato."));
    }

    public void delete(Long id) {
           if (comuneRepository.existsById(id)) {
                comuneRepository.deleteById(id);
           } else {
               throw new RuntimeException("comune non trovato.");
           }
    }

    private Users checkUserCuratore(Long userId) {
        Users curatore = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("utente non trovato."));
        if (!curatore.getRuolo().equals(Ruoli.CURATORE)) {
            throw new RuntimeException("l'utente non è un curatore.");
        }
        return curatore;
    }
}
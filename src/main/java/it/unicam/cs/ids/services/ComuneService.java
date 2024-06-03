package it.unicam.cs.ids.services;

import it.unicam.cs.ids.dto.ComuneRegistrationDto;
import it.unicam.cs.ids.exceptions.ComuneException;
import it.unicam.cs.ids.model.Comune;
import it.unicam.cs.ids.model.POI.POI;
import it.unicam.cs.ids.repository.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComuneService {

    @Autowired
    private ComuneRepository comuneRepository;

    public Comune create(ComuneRegistrationDto comuneRegistrationDto) {
        if (comuneRepository.findComuneByNome(comuneRegistrationDto.getNome()).isPresent()) {
            throw new ComuneException("il comune esiste già.");
        }

        Comune comune = new Comune(
                comuneRegistrationDto.getCap(),
                comuneRegistrationDto.getNome(),
                comuneRegistrationDto.getRegione(),
                comuneRegistrationDto.getProvincia()
        );

        return comuneRepository.save(comune);
    }

    public Comune read(Long id) {
        return comuneRepository.findById(id)
                .orElseThrow(() -> new ComuneException("comune non trovato."));
    }

    public Comune update(Long id, Comune comune) {
        return comuneRepository.findById(id).map(c -> {
            c.setNome(comune.getNome());
            c.setCap(comune.getCap());
            c.setRegione(comune.getRegione());
            c.setProvincia(comune.getProvincia());
            return comuneRepository.save(c);
        }).orElseThrow(() -> new ComuneException("comune non trovato."));
    }

    public void delete(Long id) {
           if (comuneRepository.existsById(id)) {
                comuneRepository.deleteById((long) id);
            } else {
                throw new ComuneException("comune non trovato.");
            }
    }

    public List<Comune> getAllComuni() {
        return comuneRepository.findAll();
    }

    public Comune getComuneByName(String nome) {
        return comuneRepository.findComuneByNome(nome)
                .orElseThrow(() -> new ComuneException("comune non trovato."));
    }
}
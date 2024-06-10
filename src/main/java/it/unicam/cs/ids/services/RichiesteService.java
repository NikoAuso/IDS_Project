package it.unicam.cs.ids.services;

import it.unicam.cs.ids.dto.*;
import it.unicam.cs.ids.enumeration.Ruoli;
import it.unicam.cs.ids.enumeration.StatusRichieste;
import it.unicam.cs.ids.model.Comune;
import it.unicam.cs.ids.model.POI.contenuto.Contenuto;
import it.unicam.cs.ids.model.richieste.*;
import it.unicam.cs.ids.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RichiesteService {

    @Autowired
    private AccreditamentoCuratoreRepository accreditamentoCuratoreRepository;

    @Autowired
    private AvanzamentoRuoloRepository avanzamentoRuoloRepository;

    @Autowired
    private SegnalazioneRepository segnalazioneRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ComuneRepository comuneRepository;

    @Autowired
    private ContenutoRepository contenutoRepository;

    @Autowired
    private ModificaContenutoRepository modificaContenutoRepository;
    @Autowired
    private EliminazioneContenutoRepository eliminazioneContenutoRepository;

    // Richieste AccreditamentoCuratore
    public AccreditamentoCuratore createAccreditamentoCuratore(AccreditamentoCuratoreDto accreditamentoCuratoreDto) {
        if (userService.getAuthenticatedUser().getAuthorities().contains(Ruoli.CURATORE)) {
            throw new RuntimeException("Sei già curatore di un comune.");
        }

        Comune comune = comuneRepository.findById(accreditamentoCuratoreDto.getComuneId())
                .orElseThrow(() -> new RuntimeException("Comune non trovato."));

        AccreditamentoCuratore richiesta = new AccreditamentoCuratore(
                userService.getAuthenticatedUser(),
                comune,
                accreditamentoCuratoreDto.getCommento()
        );

        return accreditamentoCuratoreRepository.save(richiesta);
    }

    public List<AccreditamentoCuratore> readAccreditamentoCuratore() {
        return accreditamentoCuratoreRepository.findAll();
    }

    public AccreditamentoCuratore readAccreditamentoCuratore(Long id) {
        return accreditamentoCuratoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Richiesta non trovata."));
    }

    public AccreditamentoCuratore updateAccreditamentoCuratore(Long id, AccreditamentoCuratoreDto accreditamentoCuratoreDto) {
        return accreditamentoCuratoreRepository.findById(id).map(richiesta -> {
            richiesta.setStatoAccreditamento(accreditamentoCuratoreDto.getStatoAccreditamento());
            richiesta.setMotivazione(accreditamentoCuratoreDto.getMotivazione());
            return accreditamentoCuratoreRepository.save(richiesta);
        }).orElseThrow(() -> new RuntimeException("Richiesta non trovata."));
    }

    public void deleteAccreditamentoCuratore(Long id) {
        if (accreditamentoCuratoreRepository.existsById(id)) {
            accreditamentoCuratoreRepository.deleteById(id);
        } else {
            throw new RuntimeException("Richiesta non trovata.");
        }
    }


    // Richieste AvanzamentoRuolo
    public AvanzamentoRuolo createAvanzamentoRuolo(AvanzamentoRuoloDto avanzamentoRuoloDto) {
        AvanzamentoRuolo richiesta = new AvanzamentoRuolo(
                userService.getAuthenticatedUser(),
                Ruoli.valueOf(avanzamentoRuoloDto.getRuoloRichiesto()),
                avanzamentoRuoloDto.getCommento()
        );

        return avanzamentoRuoloRepository.save(richiesta);
    }

    public List<AvanzamentoRuolo> readAvanzamentoRuolo() {
        return avanzamentoRuoloRepository.findAll();
    }

    public AvanzamentoRuolo readAvanzamentoRuolo(Long id) {
        return avanzamentoRuoloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Richiesta non trovata."));
    }

    public AvanzamentoRuolo updateAvanzamentoRuolo(Long id, AvanzamentoRuolo updatedAvanzamentoRuolo) {
        return avanzamentoRuoloRepository.findById(id).map(richiesta -> {
            richiesta.setStatoAvanzamento(updatedAvanzamentoRuolo.getStatoRichiesta());
            richiesta.setMotivazione(updatedAvanzamentoRuolo.getMotivazione());
            return avanzamentoRuoloRepository.save(richiesta);
        }).orElseThrow(() -> new RuntimeException("Richiesta non trovata."));
    }

    public void deleteAvanzamentoRuolo(Long id) {
        if (avanzamentoRuoloRepository.existsById(id)) {
            avanzamentoRuoloRepository.deleteById(id);
        } else {
            throw new RuntimeException("Richiesta non trovata.");
        }
    }

    // CRUD methods for Segnalazione
    public Segnalazione createSegnalazione(SegnalazioneDto segnalazioneDto) {
        if (contenutoRepository.findById(segnalazioneDto.getContenutoId())
                .orElseThrow(() -> new RuntimeException("Contenuto non trovato")) == null) {
            throw new RuntimeException("Contenuto non trovato.");
        }

        Contenuto contenuto = contenutoRepository.findById(segnalazioneDto.getContenutoId())
                .orElseThrow(() -> new RuntimeException("Contenuto non trovato"));

        Segnalazione segnalazione = new Segnalazione(
                contenuto,
                segnalazioneDto.getCommento(),
                userService.getAuthenticatedUser()
        );

        return segnalazioneRepository.save(segnalazione);
    }

    public List<Segnalazione> readSegnalazione() {
        return segnalazioneRepository.findAll();
    }

    public Segnalazione readSegnalazione(Long id) {
        return segnalazioneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Segnalazione non trovata."));
    }

    public Segnalazione updateSegnalazione(Long id, Segnalazione updatedSegnalazione) {
        return segnalazioneRepository.findById(id).map(richiesta -> {
            richiesta.setStatoSegnalazione(updatedSegnalazione.getStatoRichiesta());
            richiesta.setMotivazione(updatedSegnalazione.getMotivazione());
            return segnalazioneRepository.save(richiesta);
        }).orElseThrow(() -> new RuntimeException("Segnalazione non trovata."));
    }

    public void deleteSegnalazione(Long id) {
        if (segnalazioneRepository.existsById(id)) {
            segnalazioneRepository.deleteById(id);
        } else {
            throw new RuntimeException("Segnalazione non trovata.");
        }
    }

    // RICHIESTA MODIFICA CONTENUTO
    public ModificaContenuto createModificaContenuto(ModificaContenutoDto modificaContenutoDto) {
        if (contenutoRepository.findById(modificaContenutoDto.getContenuto())
                .orElseThrow(() -> new RuntimeException("contenuto non trovato")) == null) {
            throw new RuntimeException("Contenuto non trovato.");
        }

        Contenuto contenuto = contenutoRepository.findById(modificaContenutoDto.getContenuto())
                .orElseThrow(() -> new RuntimeException("Contenuto non trovato"));

        ModificaContenuto modificaContenuto = new ModificaContenuto(
                userService.getAuthenticatedUser(),
                contenuto,
                modificaContenutoDto.getDescrizioneModifica()
        );

        return modificaContenutoRepository.save(modificaContenuto);
    }

    public List<ModificaContenuto> readModificaContenuto() {
        return modificaContenutoRepository.findAll();
    }

    public ModificaContenuto readModificaContenuto(Long id) {
        return modificaContenutoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Richiesta non trovata."));
    }

    public ModificaContenuto updateModificaContenuto(Long id, ModificaContenutoDto modificaContenutoDto) {
        return modificaContenutoRepository.findById(id).map(richiesta -> {
            richiesta.setDescrizioneModifica(modificaContenutoDto.getDescrizioneModifica());
            return modificaContenutoRepository.save(richiesta);
        }).orElseThrow(() -> new RuntimeException("Richiesta non trovata."));
    }

    public ModificaContenuto updateModificaContenuto(Long id, StatusRichieste statoRichiesta) {
        return modificaContenutoRepository.findById(id).map(richiesta -> {
            richiesta.setStatoRichiesta(statoRichiesta);
            return modificaContenutoRepository.save(richiesta);
        }).orElseThrow(() -> new RuntimeException("Modifica contenuto non trovata."));
    }

    // RICHIESTA ELIMINAZIONE CONTENUTO
    public EliminazioneContenuto createEliminazioneContenuto(EliminazioneContenutoDto eliminazioneContenutoDto) {
        // do nothing
        return null;
    }

    public List<EliminazioneContenuto> readEliminazioneContenuto() {
        return eliminazioneContenutoRepository.findAll();
    }

    public EliminazioneContenuto readEliminazioneContenuto(Long id) {
        return eliminazioneContenutoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Richiesta non trovata."));
    }

    public EliminazioneContenuto updateEliminazioneContenuto(Long id, EliminazioneContenutoDto eliminazioneContenutoDto) {
        return eliminazioneContenutoRepository.findById(id).map(richiesta -> {
            richiesta.setMotivazioneEliminazione(eliminazioneContenutoDto.getMotivazioneEliminazione());
            return eliminazioneContenutoRepository.save(richiesta);
        }).orElseThrow(() -> new RuntimeException("Richiesta non trovata."));
    }

    public EliminazioneContenuto updateEliminazioneContenuto(Long id, StatusRichieste statoRichiesta) {
        return eliminazioneContenutoRepository.findById(id).map(richiesta -> {
            richiesta.setStatoRichiesta(statoRichiesta);
            return eliminazioneContenutoRepository.save(richiesta);
        }).orElseThrow(() -> new RuntimeException("Richiesta non trovata."));
    }

    public EliminazioneContenuto updateEliminazioneContenuto(Long id, StatusRichieste statoRichiesta, EliminazioneContenutoDto eliminazioneContenutoDto) {
        return eliminazioneContenutoRepository.findById(id).map(richiesta -> {
            richiesta.setStatoRichiesta(statoRichiesta);
            richiesta.setMotivazioneEliminazione(eliminazioneContenutoDto.getMotivazioneEliminazione());
            return eliminazioneContenutoRepository.save(richiesta);
        }).orElseThrow(() -> new RuntimeException("Richiesta non trovata."));
    }
}

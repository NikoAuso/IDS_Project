package it.unicam.cs.ids.services;

import it.unicam.cs.ids.dto.ContestDto;
import it.unicam.cs.ids.enumeration.Ruoli;
import it.unicam.cs.ids.model.Contest;
import it.unicam.cs.ids.model.POI.contenuto.ContenutoContest;
import it.unicam.cs.ids.model.Users;
import it.unicam.cs.ids.repository.ContenutoRepository;
import it.unicam.cs.ids.repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContestService {

    @Autowired
    private ContestRepository contestRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ContenutoRepository contenutoRepository;

    public Contest create(ContestDto contestDto) {
        if (contestRepository.findContestByNome(contestDto.getNome()).isPresent()) {
            throw new RuntimeException("un contest con lo stesso nome esiste già");
        }

        Contest contest = new Contest(
                contestDto.getNome(),
                contestDto.getDescrizione(),
                contestDto.getTema(),
                contestDto.getAperto(),
                userService.getAuthenticatedUser(),
                contestDto.getDataInizio(),
                contestDto.getDataFine()
        );

        return contestRepository.save(contest);
    }

    public Contest read(Long id) {
        return contestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contest non trovato"));
    }

    public Contest update(Long id, Contest contestDetails) {
        Contest contest = contestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contest non trovato"));

        contest.setNome(contestDetails.getNome());
        contest.setDescrizione(contestDetails.getDescrizione());
        contest.setDataInizio(contestDetails.getDataInizio());
        contest.setDataFine(contestDetails.getDataFine());

        return contestRepository.save(contest);
    }

    public void delete(Long id) {
        contestRepository.deleteById(id);
    }

    public List<Contest> getAllContests() {
        return contestRepository.findAll().stream().filter(
                contest -> contest.getDataFine().isAfter(LocalDate.now().atStartOfDay()) &&
                        contest.getAperto().equals(true) &&
                        contest.getUtentiPartecipanti().contains(userService.getAuthenticatedUser())
        ).toList();
    }

    public List<ContenutoContest> getAllContenutiByContest(Long contestId) {
        return contestRepository.findById(contestId)
                .orElseThrow(() -> new RuntimeException("contest non trovato"))
                .getContenuti();
    }

    public Contest partecipa(Long contestId) {
        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(() -> new RuntimeException("contest non trovato"));

        contest.getUtentiPartecipanti().add(userService.getAuthenticatedUser());

        return contestRepository.save(contest);
    }

    public String getLink(Long contestId) {
        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(() -> new RuntimeException("contest non trovato"));

        String link = "http://localhost:8080/contest/" + contest.getContestId().hashCode();
        contest.setLinkInvito(link);

        return link;
    }

    public ContenutoContest validate(Long contestId, Long contenutoId) {
        return contestRepository.findById(contestId)
                .orElseThrow(() -> new RuntimeException("contest non trovato"))
                .getContenuti().stream().filter(contenuto -> contenuto.getContenutoId().equals(contenutoId))
                .map(contenuto -> {
                    contenutoRepository.findById(contenutoId)
                            .orElseThrow(() -> new RuntimeException("contenuto non trovato"))
                            .setValidato(true);
                    return contenutoRepository.save(contenuto);
                }).findFirst().orElseThrow(() -> new RuntimeException("contenuto non trovato"));
    }

    public ContenutoContest deny(Long contestId, Long contenutoId, String motivo) {
        return contestRepository.findById(contestId)
                .orElseThrow(() -> new RuntimeException("contest non trovato"))
                .getContenuti().stream()
                .filter(contenuto -> contenuto.getContenutoId().equals(contenutoId))
                .map(contenuto -> {
                     contenuto.setMotivazione(motivo);
                    return contenutoRepository.save(contenuto);
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Contenuto non trovato"));
    }

    public String sendInvitiContest(Long contestId, List<Long> utentiId) {
        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(() -> new RuntimeException("Contest non trovato"));

        utentiId.forEach(userId -> {
            Users user = userService.read(userId);
            if (user.getRuolo().equals(Ruoli.CURATORE) || user.getRuolo().equals(Ruoli.CONTRIBUTOR)) {
                contest.getUtentiPartecipanti().add(userService.read(userId));
            }
        });
        contestRepository.save(contest);

        return "Utenti invitati con successo";
    }
}
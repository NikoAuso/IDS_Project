package it.unicam.cs.ids.services;

import it.unicam.cs.ids.dto.ContenutoDto;
import it.unicam.cs.ids.dto.ContestDto;
import it.unicam.cs.ids.enumeration.Ruoli;
import it.unicam.cs.ids.model.Contest;
import it.unicam.cs.ids.model.POI.Contenuto;
import it.unicam.cs.ids.model.POI.ContenutoContest;
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

    @Autowired
    private ContenutoService contenutoService;

    public Contest create(ContestDto contestDto) {
        if (contestRepository.findContestByNome(contestDto.getNome()).isPresent()) {
            throw new RuntimeException("un contest con lo stesso nome esiste già");
        }

        Contest contest = new Contest(
                contestDto.getNome(),
                contestDto.getDescrizione(),
                contestDto.getTema(),
                contestDto.isAperto(),
                userService.getAuthenticatedUser(),
                contestDto.getDataInizio(),
                contestDto.getDataFine()
        );

        return contestRepository.save(contest);
    }

    public Contest read(Long id) {
        return contestRepository.findById(id)
                .filter(contest -> contest.getDataFine().isAfter(LocalDate.now().atStartOfDay()))
                .filter(contest -> contest.isAperto()
                        || contest.getUtentiPartecipanti().stream().map(Users::getUserId).toList().contains(userService.getAuthenticatedUser().getUserId())
                        || contest.getAnimatore().getUserId().equals(userService.getAuthenticatedUser().getUserId()))
                .orElseThrow(() -> new RuntimeException("contest non trovato"));
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
        return contestRepository.findAll()
                .stream()
                .filter(contest -> contest.getDataFine().isAfter(LocalDate.now().atStartOfDay()))
                .filter(contest -> contest.isAperto()
                        || contest.getUtentiPartecipanti().stream().map(Users::getUserId).toList().contains(userService.getAuthenticatedUser().getUserId())
                        || contest.getAnimatore().getUserId().equals(userService.getAuthenticatedUser().getUserId()))
                .toList();
    }

    public List<ContenutoContest> getAllContenutiByContest(Long contestId) {
        return contestRepository.findById(contestId)
                .orElseThrow(() -> new RuntimeException("contest non trovato"))
                .getContenuti();
    }

    public Contest createContenutoContest(Long contestId, ContenutoDto contenutoDto) {
        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(() -> new RuntimeException("contest non trovato"));

        if(!contest.getUtentiPartecipanti().stream().map(Users::getUserId).toList().contains(userService.getAuthenticatedUser().getUserId()))
            throw new RuntimeException("non sei autorizzato a creare contenuti per questo contest");

        Contenuto contenuto = contenutoService.create(contenutoDto);
        ContenutoContest contenutoContest = new ContenutoContest(contenuto, contest);

        contenutoRepository.save(contenutoContest);
        List<ContenutoContest> contenutiContests = contest.getContenuti();
        contenutiContests.add(contenutoContest);
        contest.setContenuti(contenutiContests);
        return contestRepository.save(contest);
    }

    public Contest partecipa(Long contestId) {
        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(() -> new RuntimeException("contest non trovato"));

        List<Users> partecipanti = contest.getUtentiPartecipanti();

        if (contest.getAnimatore().getUserId().equals(userService.getAuthenticatedUser().getUserId()))
            throw new RuntimeException("sei l'animatore di questo contest");
        else if (!partecipanti.contains(userService.getAuthenticatedUser())) {
            partecipanti.add(userService.getAuthenticatedUser());
            contest.setUtentiPartecipanti(partecipanti);
        } else
            throw new RuntimeException("utente già partecipante");

        return contestRepository.save(contest);
    }

    public String getLink(Long contestId) {
        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(() -> new RuntimeException("contest non trovato"));

        String link = "http://localhost:8081/api/contest/partecipa/" + contest.getContestId();
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
                    contenuto.setValidato(false);
                    contenuto.setMotivazione(motivo);
                    return contenutoRepository.save(contenuto);
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Contenuto non trovato"));
    }

    public Contest sendInvitiContest(Long contestId, List<Long> utentiId) {
        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(() -> new RuntimeException("Contest non trovato"));

        utentiId.forEach(userId -> {
            Users user = userService.read(userId);
            if (user.getRuolo().equals(Ruoli.CURATORE) || user.getRuolo().equals(Ruoli.CONTRIBUTOR)) {
                contest.getUtentiPartecipanti().add(userService.read(userId));
            }
        });
        return contestRepository.save(contest);
    }
}
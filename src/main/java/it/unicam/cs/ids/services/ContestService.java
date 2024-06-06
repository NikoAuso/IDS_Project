package it.unicam.cs.ids.services;

import it.unicam.cs.ids.model.Contest;
import it.unicam.cs.ids.repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

public class ContestService {

    @Autowired
    private ContestRepository contestRepository;

    public Contest create(Contest contest) {
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
        return contestRepository.findAll();
    }

}
package it.unicam.cs.ids.controller;

import it.unicam.cs.ids.dto.ContestDto;
import it.unicam.cs.ids.model.Contest;
import it.unicam.cs.ids.model.POI.contenuto.ContenutoContest;
import it.unicam.cs.ids.services.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContestController {

    @Autowired
    private ContestService contestService;

    @GetMapping("/contest/all")
    public ResponseEntity<?> getAllContests() {
        List<Contest> contests = contestService.getAllContests();
        return ResponseEntity.ok(contests);
    }

    @GetMapping("/contest/{contestId}")
    public ResponseEntity<?> getContestById(@PathVariable Long contestId) {
        Contest contest = contestService.read(contestId);
        return ResponseEntity.ok(contest);
    }

    @GetMapping("/contest/{contestId}/contenuti")
    public ResponseEntity<?> getAllContenutiByContest(@PathVariable Long contestId) {
        List<ContenutoContest> contests = contestService.getAllContenutiByContest(contestId);
        return ResponseEntity.ok(contests);
    }

    @PostMapping("/api/contest/create")
    public ResponseEntity<?> createContest(@RequestBody ContestDto contestDto) {
        Contest newContest = contestService.create(contestDto);
        return ResponseEntity.ok(newContest);
    }

    @PutMapping("/api/contest/update/{id}")
    public ResponseEntity<?> updateContest(@PathVariable Long id, @RequestBody Contest contestDetails) {
        Contest updatedContest = contestService.update(id, contestDetails);
        return ResponseEntity.ok(updatedContest);
    }

    @DeleteMapping("/api/contest/delete/{id}")
    public ResponseEntity<?> deleteContest(@PathVariable Long id) {
        contestService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/api/contest/partecipa/{contestId}")
    public ResponseEntity<?> partecipaContest(@PathVariable Long contestId) {
        Contest contest = contestService.partecipa(contestId);
        return ResponseEntity.ok(contest);
    }

    @GetMapping("/api/contest/{contestId}/getLink")
    public ResponseEntity<?> getLink(@PathVariable Long contestId) {
        String link = contestService.getLink(contestId);
        return ResponseEntity.ok(link);
    }

    @PostMapping("/api/contest/{contestId}/contenuto/{contenutoId}/validate")
    public ResponseEntity<?> validateContest(@PathVariable Long contestId, @PathVariable Long contenutoId) {
        ContenutoContest contest = contestService.validate(contestId, contenutoId);
        return ResponseEntity.ok(contest);
    }

    @PostMapping("/api/contest/{contestId}/contenuto/{contenutoId}/denied")
    public ResponseEntity<?> denyContest(@PathVariable Long contestId, @PathVariable Long contenutoId, @RequestBody String motivo) {
        ContenutoContest contest = contestService.deny(contestId, contenutoId, motivo);
        return ResponseEntity.ok(contest);
    }

    @PostMapping("/api/contest/{contestId}/sendInviti")
    public ResponseEntity<?> sendInviti(@PathVariable Long contestId, @RequestBody List<Long> utentiPartecipanti) {
        return ResponseEntity.ok(contestService.sendInvitiContest(contestId, utentiPartecipanti));
    }
}
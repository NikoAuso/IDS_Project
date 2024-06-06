package it.unicam.cs.ids.controller;

import it.unicam.cs.ids.model.Contest;
import it.unicam.cs.ids.services.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contests")
public class ContestController {

    @Autowired
    private ContestService contestService;

    @PostMapping("/create")
    public ResponseEntity<Contest> createContest(@RequestBody Contest contest) {
        Contest newContest = contestService.create(contest);
        return ResponseEntity.ok(newContest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contest> getContestById(@PathVariable Long id) {
        Contest contest = contestService.read(id);
        return ResponseEntity.ok(contest);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Contest> updateContest(@PathVariable Long id, @RequestBody Contest contestDetails) {
        Contest updatedContest = contestService.update(id, contestDetails);
        return ResponseEntity.ok(updatedContest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContest(@PathVariable Long id) {
        contestService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Contest>> getAllContests() {
        List<Contest> contests = contestService.getAllContests();
        return ResponseEntity.ok(contests);
    }
}
package it.unicam.cs.ids.services;

import it.unicam.cs.ids.model.Contest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContestService {
    private final List<Contest> contestList = new ArrayList<>();

    public Contest create(Contest contest) {
        contestList.add(contest);
        return contest;
    }

    public Contest read(int id) {
        Optional<Contest> Contest = contestList.stream().filter(i -> i.getId() == id).findFirst();
        return Contest.orElse(null);
    }

    public void update(int id, Contest contest) {
        if (id >= 0 && id < contestList.size() && contestList.get(id).getId() == id) {
            contestList.set(id, contest);
        } else {
            throw new IllegalArgumentException("Contest non trovato!");
        }
    }

    public void delete(int id) {
        contestList.removeIf(i -> i.getId() == id);
    }

}
package it.unicam.cs.ids.repository;

import it.unicam.cs.ids.model.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContestRepository extends JpaRepository<Contest, Long> {
}

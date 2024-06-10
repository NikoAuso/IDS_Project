package it.unicam.cs.ids.repository;

import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import it.unicam.cs.ids.model.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ContestRepository extends JpaRepository<Contest, Long> {
    Optional<Contest> findContestByNome(String nome);
}

package it.unicam.cs.ids.repository;

import it.unicam.cs.ids.model.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComuneRepository extends JpaRepository<Comune, Long> {
    Optional<Comune> findComuneByNome(String comuneName);

    Optional<Comune> findComuneByCap(String cap);
}

package it.unicam.cs.ids.repository;

import it.unicam.cs.ids.model.richieste.Richieste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RichiesteRepository extends JpaRepository<Richieste, Long> {
}

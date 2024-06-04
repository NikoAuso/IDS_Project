package it.unicam.cs.ids.repository;

import it.unicam.cs.ids.model.richieste.Segnalazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegnalazioneRepository extends JpaRepository<Segnalazione, Long> {
}

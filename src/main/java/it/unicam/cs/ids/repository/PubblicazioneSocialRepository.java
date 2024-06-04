package it.unicam.cs.ids.repository;

import it.unicam.cs.ids.model.richieste.PubblicazioneSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PubblicazioneSocialRepository extends JpaRepository<PubblicazioneSocial, Long> {
}

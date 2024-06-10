package it.unicam.cs.ids.repository;

import it.unicam.cs.ids.model.richieste.AvanzamentoRuolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvanzamentoRuoloRepository extends JpaRepository<AvanzamentoRuolo, Long> {
}

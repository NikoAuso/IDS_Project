package it.unicam.cs.ids.repository;

import it.unicam.cs.ids.model.richieste.AccreditamentoCuratore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccreditamentoCuratoreRepository extends JpaRepository<AccreditamentoCuratore, Long> {
}

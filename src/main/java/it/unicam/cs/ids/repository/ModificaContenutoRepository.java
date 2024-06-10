package it.unicam.cs.ids.repository;

import it.unicam.cs.ids.model.richieste.ModificaContenuto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModificaContenutoRepository extends JpaRepository<ModificaContenuto, Long> {
}

package it.unicam.cs.ids.repository;

import it.unicam.cs.ids.model.POI.POI;
import it.unicam.cs.ids.model.Recensione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecensioneRepository extends JpaRepository<Recensione, Long> {
    List<Recensione> findRecensioniByPoi(POI poi);
}

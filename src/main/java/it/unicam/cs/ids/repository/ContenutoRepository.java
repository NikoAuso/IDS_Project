package it.unicam.cs.ids.repository;

import it.unicam.cs.ids.model.POI.POI;
import it.unicam.cs.ids.model.POI.contenuto.Contenuto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContenutoRepository extends JpaRepository<Contenuto, Long> {
    List<Contenuto> findContenutoByPoi(POI poi);
}

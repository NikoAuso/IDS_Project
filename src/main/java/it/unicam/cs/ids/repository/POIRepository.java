package it.unicam.cs.ids.repository;

import it.unicam.cs.ids.model.Comune;
import it.unicam.cs.ids.model.POI.POI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface POIRepository extends JpaRepository<POI, Long> {
    Optional<POI> findByLatitudineAndLongitudine(double latitudine, double longitudine);
    List<POI> findAllByComune(Comune comune);
}

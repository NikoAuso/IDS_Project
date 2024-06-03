package it.unicam.cs.ids.repository;

import it.unicam.cs.ids.model.POI.POI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface POIRepository extends JpaRepository<POI, Long> {
}

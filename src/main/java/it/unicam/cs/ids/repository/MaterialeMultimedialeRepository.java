package it.unicam.cs.ids.repository;

import it.unicam.cs.ids.model.MaterialeMultimediale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialeMultimedialeRepository extends JpaRepository<MaterialeMultimediale, Long> {
}

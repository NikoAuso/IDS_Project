package it.unicam.cs.ids.repository;

import it.unicam.cs.ids.model.Itinerario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ItinerarioRepository extends JpaRepository<Itinerario, Long> {
    Optional<Itinerario> findByNome(String nome);
}

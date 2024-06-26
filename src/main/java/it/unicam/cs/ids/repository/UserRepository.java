package it.unicam.cs.ids.repository;

import it.unicam.cs.ids.enumeration.Ruoli;
import it.unicam.cs.ids.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);

    Optional<Users> findByRuolo(Ruoli ruoli);
}

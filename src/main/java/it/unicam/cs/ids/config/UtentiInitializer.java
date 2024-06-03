package it.unicam.cs.ids.config;

import it.unicam.cs.ids.enumeration.TipoRuolo;
import it.unicam.cs.ids.model.Users;
import it.unicam.cs.ids.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtentiInitializer {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {

        // UTENTI
        Users userTurista = new Users("test", "turista", "test_turista@example.it", "test_turista", "test_turista");
        userTurista.setAutorizzato(false);
        userTurista.setRuolo(TipoRuolo.TURISTA);

        Users userContributor = new Users("test", "contributor", "test_contributor@example.it", "test_contributor", "test_contributor");
        userContributor.setAutorizzato(false);
        userContributor.setRuolo(TipoRuolo.CONTRIBUTOR);

        Users userContributorAutorizzato = new Users("test", "contributor_autorizzato", "test_contributor_autorizzato@example.it", "test_contributor_autorizzato", "test_contributor_autorizzato");
        userContributorAutorizzato.setAutorizzato(true);
        userContributorAutorizzato.setRuolo(TipoRuolo.CONTRIBUTOR);

        Users userCuratore = new Users("test", "curatore", "test_curatore@example.it", "test_curatore", "test_curatore");
        userCuratore.setAutorizzato(true);
        userCuratore.setRuolo(TipoRuolo.CURATORE);

        Users userAnimatore = new Users("test", "animatore", "test_animatore@example.it", "test_animatore", "test_animatore");
        userAnimatore.setAutorizzato(true);
        userAnimatore.setRuolo(TipoRuolo.ANIMATORE);

        Users userAdmin = new Users("test", "admin", "test_admin@example.it", "test_admin", "test_admin");
        userAdmin.setAutorizzato(true);
        userAdmin.setRuolo(TipoRuolo.ADMIN);

        userRepository.save(userTurista);
        userRepository.save(userContributor);
        userRepository.save(userContributorAutorizzato);
        userRepository.save(userCuratore);
        userRepository.save(userAnimatore);
        userRepository.save(userAdmin);
    }
}

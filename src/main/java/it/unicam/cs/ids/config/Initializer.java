package it.unicam.cs.ids.config;

import it.unicam.cs.ids.enumeration.Ruoli;
import it.unicam.cs.ids.model.Comune;
import it.unicam.cs.ids.model.Users;
import it.unicam.cs.ids.repository.ComuneRepository;
import it.unicam.cs.ids.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Initializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ComuneRepository comuneRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {

        // UTENTI
        Users userTurista = new Users("test", "turista", "test_turista@example.it", "test_turista", null);
        userTurista.setPassword(passwordEncoder.encode("test_turista"));
        userTurista.setAutorizzato(false);
        userTurista.setRuolo(Ruoli.TURISTA);

        Users userContributor = new Users("test", "contributor", "test_contributor@example.it", "test_contributor", null);
        userContributor.setPassword(passwordEncoder.encode("test_contributor"));
        userContributor.setAutorizzato(false);
        userContributor.setRuolo(Ruoli.CONTRIBUTOR);

        Users userContributorAutorizzato = new Users("test", "contributor_autorizzato", "test_contributor_autorizzato@example.it", "test_contributor_autorizzato", null);
        userContributorAutorizzato.setPassword(passwordEncoder.encode("test_contributor_autorizzato"));
        userContributorAutorizzato.setAutorizzato(true);
        userContributorAutorizzato.setRuolo(Ruoli.CONTRIBUTOR);

        Users userCuratore = new Users("test", "curatore", "test_curatore@example.it", "test_curatore", null);
        userCuratore.setPassword(passwordEncoder.encode("test_curatore"));
        userCuratore.setAutorizzato(true);
        userCuratore.setRuolo(Ruoli.CURATORE);

        Users userCuratore1 = new Users("test", "curatore_1", "test_curatore_1@example.it", "test_curatore_1", null);
        userCuratore1.setPassword(passwordEncoder.encode("test_curatore_1"));
        userCuratore1.setAutorizzato(true);
        userCuratore1.setRuolo(Ruoli.CURATORE);

        Users userCuratore2 = new Users("test", "curatore_2", "test_curatore_2@example.it", "test_curatore_2", null);
        userCuratore2.setPassword(passwordEncoder.encode("test_curatore_2"));
        userCuratore2.setAutorizzato(true);
        userCuratore2.setRuolo(Ruoli.CURATORE);

        Users userCuratore3 = new Users("test", "curatore_3", "test_curatore_3@example.it", "test_curatore_3", null);
        userCuratore3.setPassword(passwordEncoder.encode("test_curatore_3"));
        userCuratore3.setAutorizzato(true);
        userCuratore3.setRuolo(Ruoli.CURATORE);

        Users userCuratore4 = new Users("test", "curatore_4", "test_curatore_4@example.it", "test_curatore_4", null);
        userCuratore4.setPassword(passwordEncoder.encode("test_curatore_4"));
        userCuratore4.setAutorizzato(true);
        userCuratore4.setRuolo(Ruoli.CURATORE);

        Users userAnimatore = new Users("test", "animatore", "test_animatore@example.it", "test_animatore", null);
        userAnimatore.setPassword(passwordEncoder.encode("test_animatore"));
        userAnimatore.setAutorizzato(true);
        userAnimatore.setRuolo(Ruoli.ANIMATORE);

        Users userAdmin = new Users("test", "admin", "test_admin@example.it", "test_admin", null);
        userAdmin.setPassword(passwordEncoder.encode("test_admin"));
        userAdmin.setAutorizzato(true);
        userAdmin.setRuolo(Ruoli.ADMIN);

        userRepository.save(userTurista);
        userRepository.save(userContributor);
        userRepository.save(userContributorAutorizzato);
        userRepository.save(userCuratore);
        userRepository.save(userCuratore1);
        userRepository.save(userCuratore2);
        userRepository.save(userCuratore3);
        userRepository.save(userCuratore4);
        userRepository.save(userAnimatore);
        userRepository.save(userAdmin);

        // COMUNI
        Comune jesi = new Comune("60035", "Jesi", "Marche", "AN", userCuratore1);
        comuneRepository.save(jesi);

        Comune sirolo = new Comune("60020", "Sirolo", "Marche", "AN", userCuratore2);
        comuneRepository.save(sirolo);

        Comune loreto = new Comune("60025", "Loreto", "Marche", "AN", userCuratore3);
        comuneRepository.save(loreto);

        Comune ancona = new Comune("60121", "Ancona", "Marche", "AN", userCuratore4);
        comuneRepository.save(ancona);
    }
}

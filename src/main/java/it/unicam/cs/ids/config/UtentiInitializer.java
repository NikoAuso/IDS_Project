package it.unicam.cs.ids.config;

import it.unicam.cs.ids.enumeration.*;
import it.unicam.cs.ids.model.Comune;
import it.unicam.cs.ids.model.POI.POI;
import it.unicam.cs.ids.model.POI.POIFisico;
import it.unicam.cs.ids.model.POI.POILogico;
import it.unicam.cs.ids.model.POI.Contenuto;
import it.unicam.cs.ids.model.Users;
import it.unicam.cs.ids.repository.ComuneRepository;
import it.unicam.cs.ids.repository.ContenutoRepository;
import it.unicam.cs.ids.repository.POIRepository;
import it.unicam.cs.ids.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UtentiInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ComuneRepository comuneRepository;

    @Autowired
    private POIRepository poiRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ContenutoRepository contenutoRepository;

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


        // POI
        POI poiFisico = new POIFisico("Museo Archeologico", "Museo archeologico di Jesi", jesi, 13.238, 43.511, "Via del Museo, 1", null, null, "WiFi, Parcheggio", "www.museojesi.it", "", TipoCategoriePOIFisico.MUSEO, true);
        POI poiFisico2 = new POIFisico("Parco del Conero", "Parco naturale del Conero", sirolo, 13.614, 43.528, "Via del Parco, 1", null, null, "WiFi, Parcheggio", "www.parcodelconero.it", "", TipoCategoriePOIFisico.PARCO, true);
        POI poiFisico3 = new POIFisico("Basilica di Loreto", "Basilica della Santa Casa di Loreto", loreto, 13.617, 43.438, "Piazza della Madonna, 1", null, null, "WiFi, Parcheggio", "www.basilicadiloreto.it", "", TipoCategoriePOIFisico.RELIGIONE, true);
        POI poiFisico4 = new POIFisico("Museo Archeologico", "Museo archeologico di Jesi", jesi, 13.238, 43.511, "Via del Museo, 1", null, null, "WiFi, Parcheggio", "www.museojesi.it", "", TipoCategoriePOIFisico.MUSEO, false);
        POI poiFisico5 = new POIFisico("Centro benessere", "Centro benessere di Jesi", jesi, 13.238, 43.511, "Via del Benessere, 1", null, null, "WiFi, Parcheggio", "www.centrobenesserejesi.it", "", TipoCategoriePOIFisico.SALUTE_BENESSERE, false);
        POI poiFisico6 = new POIFisico("Infopoint", "Infopoint di Loreto", loreto, 13.238, 43.511, "Via dell'Infopoint, 1", null, null, "WiFi, Parcheggio", "www.infopointjesi.it", "", TipoCategoriePOIFisico.SERVIZIO_PUBBLICO, true);
        POI poiFisico7 = new POIFisico("Stazione ferroviaria", "Stazione ferroviaria di Ancona", ancona, 13.238, 43.511, "Via della Stazione, 1", null, null, "WiFi, Parcheggio", "www.stazioneancona.it", "", TipoCategoriePOIFisico.STAZIONE, false);

        POI poiLogico = new POILogico("Jesi", "Jesi è un comune italiano di 39 457 abitanti della provincia di Ancona nelle Marche. Posizionata lungo il medio corso del fiume Esino, è una città di antiche tradizioni industriali.", jesi, 13.238, 43.511, "Informazioni storiche", "Area", TipoCategoriePOILogico.QUARTIERE, true);
        POI poiLogico2 = new POILogico("Sirolo", "Sirolo è un comune italiano di 3 800 abitanti della provincia di Ancona nelle Marche. Sorge su un promontorio a picco sul mare Adriatico.", sirolo, 13.614, 43.528, "Informazioni storiche", "Area", TipoCategoriePOILogico.QUARTIERE, false);
        POI poiLogico3 = new POILogico("Loreto", "Loreto è un comune italiano di 12 000 abitanti della provincia di Ancona nelle Marche. È famoso per la Basilica della Santa Casa.", loreto, 13.617, 43.438, "Informazioni storiche", "Area", TipoCategoriePOILogico.QUARTIERE, true);
        POI poiLogico4 = new POILogico("Ancona", "Ancona è un comune italiano di 100 000 abitanti della provincia di Ancona nelle Marche. È il capoluogo della regione.", ancona, 13.238, 43.511, "Informazioni storiche", "Area", TipoCategoriePOILogico.COMUNE, false);

        poiRepository.save(poiFisico);
        poiRepository.save(poiFisico2);
        poiRepository.save(poiFisico3);
        poiRepository.save(poiFisico4);
        poiRepository.save(poiFisico5);
        poiRepository.save(poiFisico6);
        poiRepository.save(poiFisico7);
        poiRepository.save(poiLogico);
        poiRepository.save(poiLogico2);
        poiRepository.save(poiLogico3);
        poiRepository.save(poiLogico4);


        // CONTENUTI
        Contenuto contenuto = new Contenuto.Builder()
                .setPOI(poiFisico)
                .setTipo(TipoContenuto.STATICO)
                .setCategoria(TipoCategoriaContenuto.CULTURALE)
                .setAutore(userContributor)
                .setValidato(userContributor.getAutorizzato())
                .setTitolo("Museo Archeologico")
                .setDescrizione("Il Museo Archeologico di Jesi è un museo archeologico situato a Jesi, nelle Marche.")
                .setUrl("https://www.museojesi.it/")
                .build();
        contenutoRepository.save(contenuto);
    }
}

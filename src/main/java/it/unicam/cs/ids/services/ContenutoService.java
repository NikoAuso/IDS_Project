package it.unicam.cs.ids.services;

import it.unicam.cs.ids.dto.ContenutoDto;
import it.unicam.cs.ids.enumeration.TipoCategoriaContenuto;
import it.unicam.cs.ids.enumeration.TipoContenuto;
import it.unicam.cs.ids.model.POI.POI;
import it.unicam.cs.ids.model.POI.Contenuto;
import it.unicam.cs.ids.model.Users;
import it.unicam.cs.ids.observer.ObserverImpl;
import it.unicam.cs.ids.observer.Publisher;
import it.unicam.cs.ids.repository.ContenutoRepository;
import it.unicam.cs.ids.repository.POIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContenutoService extends Publisher {

    @Autowired
    private ContenutoRepository contenutoRepository;

    @Autowired
    private POIRepository poiRepository;

    @Autowired
    private UserService userService;

    private final ObserverImpl observer = new ObserverImpl();

    public Contenuto create(ContenutoDto contenutoDto) {
        addObserver(observer);

        POI poi = poiRepository.findById(contenutoDto.getPoiId())
                .orElseThrow(() -> new RuntimeException("POI non trovato."));
        Users autore = userService.read(contenutoDto.getAutoreId());

        Contenuto contenuto;
        TipoContenuto tipoContenuto = TipoContenuto.valueOf(contenutoDto.getTipo());

        switch (tipoContenuto) {
            case TipoContenuto.TEMPORIZZATO -> contenuto = new Contenuto.Builder()
                    .setPOI(poi)
                    .setTipo(tipoContenuto)
                    .setCategoria(TipoCategoriaContenuto.valueOf(contenutoDto.getCategoria().toUpperCase()))
                    .setAutore(autore)
                    .setTitolo(contenutoDto.getTitolo())
                    .setDescrizione(contenutoDto.getDescrizione())
                    .setUrl(contenutoDto.getUrl())
                    .setDataInizio(contenutoDto.getDataInizio())
                    .setDataFine(contenutoDto.getDataFine())
                    .setNote(contenutoDto.getNote())
                    .setValidato(userService.getAuthenticatedUser().getAutorizzato()) // basandosi sui dettagli dell'utente autenticato preso dalla sessione
                    .build();
            case TipoContenuto.STATICO -> contenuto = new Contenuto.Builder()
                    .setPOI(poi)
                    .setTipo(tipoContenuto)
                    .setCategoria(TipoCategoriaContenuto.valueOf(contenutoDto.getCategoria().toUpperCase()))
                    .setAutore(autore)
                    .setTitolo(contenutoDto.getTitolo())
                    .setDescrizione(contenutoDto.getDescrizione())
                    .setUrl(contenutoDto.getUrl())
                    .setNote(contenutoDto.getNote())
                    .setValidato(userService.getAuthenticatedUser().getAutorizzato()) // basandosi sui dettagli dell'utente autenticato preso dalla sessione
                    .build();
            default -> throw new RuntimeException("tipo contenuto non valido.");
        }

        Users toNotify = contenuto.getPoi().getComune().getCuratore();
        notifyObservers(toNotify, "Contenuto creato");

        removeObserver(observer);

        return contenutoRepository.save(contenuto);
    }

    public Contenuto read(Long id) {
        return contenutoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("contenuto non trovato."));
    }

    public Contenuto update(Long id, ContenutoDto contenutoDto) {
        if (!userService.getAuthenticatedUser().getAutorizzato())
            throw new RuntimeException("utente non autorizzato.");

        Contenuto contenuto = contenutoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("contenuto non trovato."));

        contenuto.setTipo(TipoContenuto.valueOf(contenutoDto.getTipo().toUpperCase()));
        contenuto.setCategoria(TipoCategoriaContenuto.valueOf(contenutoDto.getCategoria().toUpperCase()));
        contenuto.setTitolo(contenutoDto.getTitolo());
        contenuto.setDescrizione(contenutoDto.getDescrizione());
        contenuto.setUrl(contenutoDto.getUrl());
        contenuto.setDataInizio(contenutoDto.getDataInizio());
        contenuto.setDataFine(contenutoDto.getDataFine());
        contenuto.setNote(contenutoDto.getNote());

        contenutoRepository.save(contenuto);
        return contenuto;
    }

    public void delete(Long id) {
        if(contenutoRepository.existsById(id)) {
            contenutoRepository.deleteById(id);
        } else {
            throw new RuntimeException("contenuto non trovato.");
        }
    }

    public List<Contenuto> getAllContenutiByPOI(Long poiId) {
        return contenutoRepository.findContenutoByPoi(poiRepository.findById(poiId)
                .orElseThrow(() -> new RuntimeException("POI non trovato.")));
    }

    public Contenuto validate(Long contenutoId) {
        return contenutoRepository.findById(contenutoId)
                .filter(c -> !c.isValidato())
                .map(c -> {
                    c.setValidato(true);
                    return contenutoRepository.save(c);
                })
                .orElseThrow(() -> new RuntimeException("contenuto non trovato."));
    }
}
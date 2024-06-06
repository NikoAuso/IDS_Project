package it.unicam.cs.ids.services;

import it.unicam.cs.ids.dto.ContenutoDto;
import it.unicam.cs.ids.enumeration.TipoContenuto;
import it.unicam.cs.ids.model.POI.POI;
import it.unicam.cs.ids.model.POI.contenuto.Contenuto;
import it.unicam.cs.ids.model.Users;
import it.unicam.cs.ids.repository.ContenutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContenutoService {

    @Autowired
    private ContenutoRepository contenutoRepository;

    @Autowired
    private POIService poiService;

    @Autowired
    private UserService userService;


    public Contenuto create(ContenutoDto contenutoDto) {
        POI poi = poiService.read(contenutoDto.getPoiId());
        Users autore = userService.read(contenutoDto.getAutoreId());

        Contenuto contenuto;

        switch (contenutoDto.getTipo()) {
            case TipoContenuto.TEMPORIZZATO:
                contenuto = new Contenuto.Builder()
                        .setPOI(poi)
                        .setTipo(contenutoDto.getTipo())
                        .setAutore(autore)
                        .setValidato(contenutoDto.isValidato())
                        .setTitolo(contenutoDto.getTitolo())
                        .setDescrizione(contenutoDto.getDescrizione())
                        .setUrl(contenutoDto.getUrl())
                        .setDataInizio(contenutoDto.getDataInizio())
                        .setDataFine(contenutoDto.getDataFine())
                        .setNote(contenutoDto.getNote())
                        .build();
                contenutoRepository.save(contenuto);
            case TipoContenuto.STATICO:
                contenuto = new Contenuto.Builder()
                        .setPOI(poi)
                        .setTipo(contenutoDto.getTipo())
                        .setAutore(autore)
                        .setValidato(contenutoDto.isValidato())
                        .setTitolo(contenutoDto.getTitolo())
                        .setDescrizione(contenutoDto.getDescrizione())
                        .setUrl(contenutoDto.getUrl())
                        .setNote(contenutoDto.getNote())
                        .build();
                contenutoRepository.save(contenuto);
            default:
                throw new IllegalArgumentException("Tipo contenuto non valido.");
        }
    }

    public Contenuto read(Long id) {
        return contenutoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contenuto non trovato."));
    }

    public Contenuto update(Long id, ContenutoDto contenutoDto) {
        Contenuto contenuto = contenutoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contenuto non trovato."));

        POI poi = poiService.read(contenutoDto.getPoiId());
        Users autore = userService.read(contenutoDto.getAutoreId());

        contenuto.setTitolo(contenutoDto.getTitolo());
        contenuto.setDescrizione(contenutoDto.getDescrizione());
        contenuto.setUrl(contenutoDto.getUrl());
        contenuto.setDataInizio(contenutoDto.getDataInizio());
        contenuto.setDataFine(contenutoDto.getDataFine());
        contenuto.setNote(contenutoDto.getNote());
        contenuto.setValidato(contenutoDto.isValidato());

        contenutoRepository.save(contenuto);
        return contenuto;
    }


    public void delete(Long id) {
        contenutoRepository.deleteById(id);
    }

    public List<Contenuto> getAllContenutiByPOI(Long poiId) {
        POI poi = poiService.read(poiId);
        return contenutoRepository.findContenutoByPoi(poi);
    }


}
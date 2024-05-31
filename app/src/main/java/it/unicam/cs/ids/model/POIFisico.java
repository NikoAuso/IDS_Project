package it.unicam.cs.ids.model;

import it.unicam.cs.ids.enumClasses.TipoCategorieFisico;
import it.unicam.cs.ids.enumClasses.TipoPOI;

import java.util.ArrayList;

public class POIFisico extends POI {
    private String indirizzo;
    private String orariDiApertura;
    private String serviziDispinibili;
    private String sitoWeb;
    private String contatti;

    private TipoCategorieFisico categoria;

    public POIFisico(ArrayList<?> dati){
        super(
                (String) dati.get(1), // nome
                (String) dati.get(2), // descrizione
                (boolean) dati.get(3), // validato
                (Comune) dati.get(4), // comune
                (double) dati.get(5), // longitudine
                (double) dati.get(6) // latitudine
        );
        this.indirizzo = dati.get(7).toString();
        this.orariDiApertura = dati.get(8).toString();
        this.serviziDispinibili = dati.get(9).toString();
        this.sitoWeb = dati.get(10).toString();
        this.contatti = dati.get(11).toString();

        this.categoria = TipoCategorieFisico.valueOf(String.valueOf(dati.get(12)));
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getOrariDiApertura() {
        return orariDiApertura;
    }

    public void setOrariDiApertura(String orariDiApertura) {
        this.orariDiApertura = orariDiApertura;
    }

    public String getServiziDispinibili() {
        return serviziDispinibili;
    }

    public void setServiziDispinibili(String serviziDispinibili) {
        this.serviziDispinibili = serviziDispinibili;
    }

    public String getSitoWeb() {
        return sitoWeb;
    }

    public void setSitoWeb(String sitoWeb) {
        this.sitoWeb = sitoWeb;
    }

    public String getContatti() {
        return contatti;
    }

    public void setContatti(String contatti) {
        this.contatti = contatti;
    }

    public TipoCategorieFisico getCategoria() {
        return categoria;
    }

    public void setCategoria(TipoCategorieFisico categoria) {
        this.categoria = categoria;
    }

    @Override
    public TipoPOI getType() {
        return TipoPOI.FISICO;
    }
}

package it.unicam.cs.ids.model;

import it.unicam.cs.ids.enumClasses.TipoCategorieLogico;
import it.unicam.cs.ids.enumClasses.TipoPOI;

import java.util.ArrayList;

public class POILogico extends POI{
    private String informazioniStoriche;
    private String area;

    private TipoCategorieLogico categoria;

    public POILogico(ArrayList<?> dati) {
        super(
                (String) dati.get(1), // nome
                (String) dati.get(2), // descrizione
                (boolean) dati.get(3), // validato
                (Comune) dati.get(4), // comune
                (double) dati.get(5), // longitudine
                (double) dati.get(6) // latitudine
        );
        this.informazioniStoriche = (String) dati.get(7);
        this.area = (String) dati.get(8);
        this.categoria = TipoCategorieLogico.valueOf(String.valueOf(dati.get(9)));
    }

    public String getInformazioniStoriche() {
        return informazioniStoriche;
    }

    public void setInformazioniStoriche(String informazioniStoriche) {
        this.informazioniStoriche = informazioniStoriche;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public TipoCategorieLogico getCategoria() {
        return categoria;
    }

    public void setCategoria(TipoCategorieLogico categoria) {
        this.categoria = categoria;
    }


    @Override
    public TipoPOI getType() {
        return TipoPOI.LOGICO;
    }
}

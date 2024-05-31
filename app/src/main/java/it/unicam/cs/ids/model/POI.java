package it.unicam.cs.ids.model;

import it.unicam.cs.ids.enumClasses.TipoPOI;

import java.util.List;

public abstract class POI {
    private int id;
    private String nome;
    private String descrizione;

    private double longitudine;
    private double latitudine;

    private boolean validato;

    private Comune comune;

    private List<Contenuto> contenuti;

    private List<Recensione> recensioni;

    public POI(String nome, String descrizione, boolean validato, Comune comune, double longitudine, double latitudine) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.validato = validato;
        this.comune = comune;
        this.longitudine = longitudine;
        this.latitudine = latitudine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public boolean isValidato() {
        return validato;
    }

    public void setValidato(boolean validato) {
        this.validato = validato;
    }

    public Comune getComune() {
        return comune;
    }

    public void setComune(Comune comune) {
        this.comune = comune;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public abstract TipoPOI getType();
}

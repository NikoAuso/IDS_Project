package it.unicam.cs.ids.model;

import java.util.List;

public class POI {
    private int id;
    private String nome;
    private String descrizione;

    private double longitudine;
    private double latitudine;

    private boolean validato;

    private Comune comune;

    private boolean isPhysical;

    // Per i POI fisici
    private String orariDiApertura;

    // Per i POI logici
    private String informazioniStoriche;

    private List<Contenuto> contenuti;

    private List<Recensione> recensioni;

    public POI(int id, String nome, String descrizione, boolean validato, Comune comune, double longitudine, double latitudine, boolean isPhysical, String orariDiApertura, String informazioniStoriche, List<Contenuto> contenuti, List<Recensione> recensioni) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.validato = validato;
        this.comune = comune;
        this.longitudine = longitudine;
        this.latitudine = latitudine;
        this.isPhysical = isPhysical;
        this.orariDiApertura = orariDiApertura;
        this.informazioniStoriche = informazioniStoriche;
        this.contenuti = contenuti;
        this.recensioni = recensioni;
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

    public boolean isPhysical() {
        return isPhysical;
    }

    public void setPhysical(boolean physical) {
        isPhysical = physical;
    }

    public String getOrariDiApertura() {
        return orariDiApertura;
    }

    public void setOrariDiApertura(String orariDiApertura) {
        this.orariDiApertura = orariDiApertura;
    }

    public String getInformazioniStoriche() {
        return informazioniStoriche;
    }

    public void setInformazioniStoriche(String informazioniStoriche) {
        this.informazioniStoriche = informazioniStoriche;
    }

    public List<?> getContenuti() {
        return contenuti;
    }

    public void setContenuti(List<Contenuto> contenuti) {
        this.contenuti = contenuti;
    }

    public List<Recensione> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(List<Recensione> recensioni) {
        this.recensioni = recensioni;
    }
}

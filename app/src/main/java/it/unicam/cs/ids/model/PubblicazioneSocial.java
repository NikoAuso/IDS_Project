package it.unicam.cs.ids.model;

import java.util.ArrayList;
import java.util.List;

public class PubblicazioneSocial implements Richieste {
    private int id;
    private String titolo;
    private String decrizione;
    private List<Contenuto> contenuti;
    private List<String> social;

    private User utente;
    private User curatore;

    private String statoRichiesta;

    public PubblicazioneSocial(ArrayList<?> dati) {
        this.titolo = dati.get(0).toString();
        this.decrizione = dati.get(1).toString();
        this.contenuti = (List<Contenuto>) dati.get(2);
        this.social = (List<String>) dati.get(3);
        this.utente = (User) dati.get(4);
        this.curatore = (User) dati.get(5);
        this.statoRichiesta = dati.get(6).toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDecrizione() {
        return decrizione;
    }

    public void setDecrizione(String decrizione) {
        this.decrizione = decrizione;
    }

    public List<Contenuto> getContenuti() {
        return contenuti;
    }

    public void setContenuti(List<Contenuto> contenuti) {
        this.contenuti = contenuti;
    }

    public List<String> getSocial() {
        return social;
    }

    public void setSocial(List<String> social) {
        this.social = social;
    }

    @Override
    public String statoRichiesta() {
        return statoRichiesta;
    }

    public void setStatoRichiesta(String statoRichiesta) {
        this.statoRichiesta = statoRichiesta;
    }

    @Override
    public User getFrom() {
        return utente;
    }

    public void setUtente(User utente) {
        this.utente = utente;
    }

    @Override
    public User getTo() {
        return curatore;
    }

    public void setCuratore(User curatore) {
        this.curatore = curatore;
    }
}

package it.unicam.cs.ids.model.richieste;

import it.unicam.cs.ids.model.Contenuto;
import it.unicam.cs.ids.model.Users;

import java.util.ArrayList;
import java.util.List;

public class PubblicazioneSocial implements Richieste {
    private int id;
    private String titolo;
    private String descrizione;
    private List<Contenuto> contenuti;
    private List<String> social;

    private Users curatore;

    private String statoRichiesta;

    public PubblicazioneSocial(ArrayList<?> dati) {
        this.titolo = dati.get(0).toString();
        this.descrizione = dati.get(1).toString();
        this.contenuti = null;
        this.social = null;
        this.curatore = (Users) dati.get(5);
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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
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
    public String getStatoRichiesta() {
        return statoRichiesta;
    }

    public void setStatoRichiesta(String statoRichiesta) {
        this.statoRichiesta = statoRichiesta;
    }

    @Override
    public Users getFrom() {
        return curatore;
    }

    public void setFrom(Users users) {
        this.curatore = users;
    }

    @Override
    public Users getTo() {
        return null;
    }
}

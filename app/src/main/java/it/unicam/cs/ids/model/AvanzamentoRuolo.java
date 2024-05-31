package it.unicam.cs.ids.model;

import java.util.ArrayList;

public class AvanzamentoRuolo implements Richieste{
    private int id;
    private User utente;
    private String commento;

    private String statoRichiesta;

    public AvanzamentoRuolo(ArrayList<?> dati) {
        this.utente = (User) dati.get(0);
        this.commento = dati.get(1).toString();
        this.statoRichiesta = dati.get(2).toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
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
        return null;
    }
}

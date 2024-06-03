package it.unicam.cs.ids.model.richieste;

import it.unicam.cs.ids.model.Users;

import java.util.ArrayList;

public class AvanzamentoRuolo implements Richieste{
    private int id;
    private Users richiedente;
    private String commento;

    private String statoRichiesta;

    public AvanzamentoRuolo(ArrayList<?> dati) {
        this.richiedente = (Users) dati.get(0);
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
    public String getStatoRichiesta() {
        return statoRichiesta;
    }

    public void setStatoRichiesta(String statoRichiesta) {
        this.statoRichiesta = statoRichiesta;
    }

    @Override
    public Users getFrom() {
        return richiedente;
    }

    public void setFrom(Users richiedente) {
        this.richiedente = richiedente;
    }

    @Override
    public Users getTo() {
        return null;
    }
}

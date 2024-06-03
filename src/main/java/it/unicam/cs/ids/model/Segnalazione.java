package it.unicam.cs.ids.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Segnalazione {
    private int id;
    private Contenuto contenuto;

    private String dettagli;

    private Users autore;
    private String statoSegnalazione;

    private LocalDateTime data;

    public Segnalazione(ArrayList<?> dati) {
        this.data = (LocalDateTime) dati.get(0);
        this.statoSegnalazione = dati.get(1).toString();
        this.autore = (Users) dati.get(2);
        this.dettagli = dati.get(3).toString();
        this.contenuto = (Contenuto) dati.get(4);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Contenuto getContenuto() {
        return contenuto;
    }

    public void setContenuto(Contenuto contenuto) {
        this.contenuto = contenuto;
    }

    public String getDettagli() {
        return dettagli;
    }

    public void setDettagli(String dettagli) {
        this.dettagli = dettagli;
    }

    public Users getAutore() {
        return autore;
    }

    public void setAutore(Users autore) {
        this.autore = autore;
    }

    public String getStatoSegnalazione() {
        return statoSegnalazione;
    }

    public void setStatoSegnalazione(String statoSegnalazione) {
        this.statoSegnalazione = statoSegnalazione;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}

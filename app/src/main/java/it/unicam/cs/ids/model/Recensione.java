package it.unicam.cs.ids.model;

public class Recensione {
    private int id;
    //TODO: Cambiare tipo in User
    private String autore;

    private String commento;

    private int voto;

    private String created_at;

    public Recensione(int id, String autore, String commento, int voto, String created_at) {
        this.id = id;
        this.autore = autore;
        this.commento = commento;
        this.voto = voto;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}

package it.unicam.cs.ids.model;

public class Recensione {
    private int id;

    private Users autore;

    private String commento;

    private int voto;

    private String created_at;

    public Recensione(int id, Users autore, String commento, int voto, String created_at) {
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

    public Users getAutore() {
        return autore;
    }

    public void setAutore(Users autore) {
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

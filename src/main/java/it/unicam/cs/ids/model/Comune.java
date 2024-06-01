package it.unicam.cs.ids.model;

public class Comune {
    private Integer id;
    private String nome;
    private String cap;
    private String regione;
    private String provincia;
    private User curatore;

    public Comune(Integer id, String nome, String cap, String regione, String provincia, User curatore) {
        this.id = id;
        this.nome = nome;
        this.cap = cap;
        this.regione = regione;
        this.provincia = provincia;
        this.curatore = curatore;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public User getCuratore() {
        return curatore;
    }

    public void setCuratore(User curatore) {
        this.curatore = curatore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

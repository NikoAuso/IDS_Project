package it.unicam.cs.ids.model;

import java.time.LocalDateTime;
import java.util.List;

public class Contest {
    private Integer id;
    private String nome;
    private String descrizione;
    private Boolean tipo;
    private String tema;
    private List<Contenuto> contenuti;
    private List<User> utenti;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private User curatore;

    // Costruttori, getters e setters

    public Contest(Integer id, String nome, String descrizione, Boolean tipo, String tema, List<Contenuto> contenuti, List<User> utenti, LocalDateTime dataInizio, LocalDateTime dataFine, User curatore) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipo = tipo;
        this.tema = tema;
        this.contenuti = contenuti;
        this.utenti = utenti;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.curatore = curatore;
    }

    // Getters e setters


    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDateTime getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDateTime dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDateTime getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDateTime dataFine) {
        this.dataFine = dataFine;
    }

    public User getCuratore() {
        return curatore;
    }

    public void setCuratore(User curatore) {
        this.curatore = curatore;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getTipo() {
        return tipo;
    }

    public void setTipo(Boolean tipo) {
        this.tipo = tipo;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public List<Contenuto> getContenuti() {
        return contenuti;
    }

    public void setContenuti(List<Contenuto> contenuti) {
        this.contenuti = contenuti;
    }

    public List<User> getUtenti() {
        return utenti;
    }

    public void setUtenti(List<User> utenti) {
        this.utenti = utenti;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
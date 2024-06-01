package it.unicam.cs.ids.model;

import it.unicam.cs.ids.enumeration.TipoRuolo;

import java.util.List;

public class User {
    private Integer id;
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private String email;
    private Boolean autorizzato;
    private TipoRuolo ruolo;
    private List<POI> preferiti;

    public User(Integer id, String nome, String cognome, String username, String password, String email, Boolean autorizzato, TipoRuolo ruolo, List<POI> preferiti) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.email = email;
        this.autorizzato = autorizzato;
        this.ruolo = ruolo;
        this.preferiti = preferiti;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<POI> getPreferiti() {
        return preferiti;
    }

    public void setPreferiti(List<POI> preferiti) {
        this.preferiti = preferiti;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoRuolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(TipoRuolo ruolo) {
        this.ruolo = ruolo;
    }

    public boolean autorizzato() {
        return autorizzato;
    }

    public void setAutorizzato(boolean autorizzato) {
        this.autorizzato = autorizzato;
    }
}

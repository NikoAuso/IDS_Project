package it.unicam.cs.ids.model;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
public class Contenuto {
    private Integer id;
    private String nome;
    private String descrizione;
    private TipoContenuto tipo; // es: "culturale", "turistico", "sportivo", ecc.
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private boolean validato;
    private List<File> files;
    private User created_by;
    private LocalDateTime created_at;
    private String updated_by;
    private LocalDateTime updated_at;


    // Costruttore, getters e setters

    public Contenuto(Integer id, String nome, String descrizione, TipoContenuto tipo, LocalDateTime dataInizio,
                     LocalDateTime dataFine, boolean validato, List<File> files, User created_by,
                     LocalDateTime created_at,String updated_by, LocalDateTime updated_at ){
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipo = tipo;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.validato = validato;
        this.files = files ;
        this.created_by = created_by;
        this.created_at = created_at;
        this.updated_by = updated_by;
        this.updated_at = updated_at;
    }

    // Getters e setters

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
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public TipoContenuto getTipo() {
        return tipo;
    }

    public void setTipo(TipoContenuto tipo) {
        this.tipo = tipo;
    }


    public boolean isValidato() {
        return validato;
    }

    public void setValidato(boolean validato) {
        this.validato = validato;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
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

    public User getCreated_by() {
        return created_by;
    }

    public void setCreated_by(User created_by) {
        this.created_by = created_by;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
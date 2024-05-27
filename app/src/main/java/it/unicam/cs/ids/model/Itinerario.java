package it.unicam.cs.ids.model;

import java.util.List;

public class Itinerario {
    private int id;
    private String nome;
    private String descrizione;
    private String distanza;

    private List<POI> percorso;

    //TODO: Cambiare in tipo User
    private String autore;

    public Itinerario(int id, String nome, String descrizione, String distanza, List<POI> percorso, String autore) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.distanza = distanza;
        this.percorso = percorso;
        this.autore = autore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getDistanza() {
        return distanza;
    }

    public void setDistanza(String distanza) {
        this.distanza = distanza;
    }

    public List<POI> getPointsOfInterest() {
        return percorso;
    }

    public void setPointsOfInterest(List<POI> pointsOfInterest) {
        this.percorso = pointsOfInterest;
    }

    public void addPointOfInterest(POI poi) {
        this.percorso.add(poi);
    }

    public void removePointOfInterest(POI poi) {
        this.percorso.remove(poi);
    }


    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }
}

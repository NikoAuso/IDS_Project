package it.unicam.cs.ids.model;

public interface Richieste {
    int getId();
    String statoRichiesta();
    User getFrom();
    User getTo();
}

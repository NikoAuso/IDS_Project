package it.unicam.cs.ids.model.POI.contenuto;

import it.unicam.cs.ids.enumeration.TipoCategoriaContenuto;
import it.unicam.cs.ids.enumeration.TipoContenuto;
import it.unicam.cs.ids.model.POI.POI;
import it.unicam.cs.ids.model.Users;
import it.unicam.cs.ids.model.richieste.Segnalazione;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Contenuto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contenutoId;

    @ManyToOne
    @JoinColumn(name = "poi", nullable = false)
    private POI poi;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoContenuto tipo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoCategoriaContenuto categoria;

    @ManyToOne
    @JoinColumn(name = "autore", nullable = false)
    private Users autore;

    @Column(nullable = false)
    private boolean validato;

    @Column(nullable = false)
    private String titolo;

    @Column(nullable = false)
    private String descrizione;

    // Campi aggiuntivi per altri tipi di contenuto
    private String url;
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private String note;

    @OneToMany(mappedBy = "segnalazioneId", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Segnalazione> segnalazioni;

    @CreationTimestamp
    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    public static class Builder {
        private POI poi;
        private TipoContenuto tipo;
         private TipoCategoriaContenuto categoria;
        private Users autore;
        private boolean validato;
        private String titolo;
        private String descrizione;

        private String url;

        private LocalDateTime dataInizio;
        private LocalDateTime dataFine;

        private String note;;

        public Builder setPOI(POI poi) {
            this.poi = poi;
            return this;
        }

        public Builder setTipo(TipoContenuto tipo) {
            this.tipo = tipo;
            return this;
        }

        public Builder setCategoria(TipoCategoriaContenuto categoria) {
            this.categoria = categoria;
            return this;
        }

        public Builder setAutore(Users autore) {
            this.autore = autore;
            return this;
        }

        public Builder setValidato(boolean validato) {
            this.validato = validato;
            return this;
        }

        public Builder setTitolo(String titolo) {
            this.titolo = titolo;
            return this;
        }

        public Builder setDescrizione(String descrizione) {
            this.descrizione = descrizione;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setDataInizio(LocalDateTime dataInizio) {
            this.dataInizio = dataInizio;
            return this;
        }

        public Builder setDataFine(LocalDateTime dataFine) {
            this.dataFine = dataFine;
            return this;
        }

        public Builder setNote(String note) {
            this.note = note;
            return this;
        }

        public Contenuto build() {
            Contenuto contenuto = new Contenuto();
            contenuto.setPoi(this.poi);
            contenuto.setTipo(this.tipo);
            contenuto.setCategoria(this.categoria);
            contenuto.setAutore(this.autore);
            contenuto.setValidato(this.validato);
            contenuto.setTitolo(this.titolo);
            contenuto.setDescrizione(this.descrizione);
            contenuto.setUrl(this.url);
            contenuto.setDataInizio(this.dataInizio);
            contenuto.setDataFine(this.dataFine);
            contenuto.setNote(this.note);
            return contenuto;
        }
    }
}

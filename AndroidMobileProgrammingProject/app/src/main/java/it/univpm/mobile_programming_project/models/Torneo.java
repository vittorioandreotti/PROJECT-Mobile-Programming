package it.univpm.mobile_programming_project.models;

import java.util.Date;

public class Torneo {
    private String id;
    private String titolo;
    private String categoria;
    private String indirizzo;
    private Date dataOra;
    private String regolamento;

    public Torneo(){}

    public Torneo(String id, String titolo, String categoria, String indirizzo, Date dataOra, String regolamento) {
        this.id = id;
        this.titolo = titolo;
        this.categoria = categoria;
        this.indirizzo = indirizzo;
        this.dataOra = dataOra;
        this.regolamento = regolamento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Date getDataOra() {
        return dataOra;
    }

    public void setDataOra(Date dataOra) {
        this.dataOra = dataOra;
    }

    public String getRegolamento() {
        return regolamento;
    }

    public void setRegolamento(String regolamento) {
        this.regolamento = regolamento;
    }
}

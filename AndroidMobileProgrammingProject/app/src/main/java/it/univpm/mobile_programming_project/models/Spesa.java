package it.univpm.mobile_programming_project.models;

import java.util.Date;
import java.util.Map;

import it.univpm.mobile_programming_project.utils.Helper;

public class Spesa {

    private String idSpesa;
    private String idUtente;
    private String nome;
    private String descrizione;
    private String categoria;
    private String titolo;
    private String tipo;
    private Double prezzo;
    private Date dataInserimento;
    private Date dataPagamento;
    private Date dataScadenza;
    private String nomeUtente;
    private String cognomeUtente;

    public Spesa() {}

    public Boolean isSpesaPagata() {
        return this.dataPagamento != null;
    }

    public String getIdSpesa() {
        return idSpesa;
    }

    public void setIdSpesa(String idSpesa) {
        this.idSpesa = idSpesa;
    }

    public String getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(String idUtente) {
        this.idUtente = idUtente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public Date getDataInserimento() {
        return dataInserimento;
    }

    public void setDataInserimento(Date dataInserimento) {
        this.dataInserimento = dataInserimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public String getCognomeUtente() {
        return cognomeUtente;
    }

    public void setCognomeUtente(String cognomeUtente) {
        this.cognomeUtente = cognomeUtente;
    }

    public Date getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(Date dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public void createFromHashMap(Map<String, Object> spesaSingola) {

        this.setPrezzo(Double.valueOf(spesaSingola.get("prezzo").toString()));
        this.setIdSpesa((String)spesaSingola.get("idSpesa"));
        this.setDescrizione((String)spesaSingola.get("descrizione"));
        this.setTipo((String)spesaSingola.get("tipo"));
        this.setTitolo((String)spesaSingola.get("titolo"));
        this.setCategoria((String)spesaSingola.get("categoria"));

        this.setNomeUtente( (String)spesaSingola.get("nomeUtente") );
        this.setCognomeUtente( (String)spesaSingola.get("cognomeUtente") );

        this.setDataInserimento(Helper.fromMillisToDate((Long)spesaSingola.get("dataInserimento")));
        this.setDataPagamento(Helper.fromMillisToDate((Long)spesaSingola.get("dataPagamento")));
        this.setDataScadenza(Helper.fromMillisToDate((Long)spesaSingola.get("dataScadenza")));

        this.setNome((String)spesaSingola.get("nome"));
        this.setIdUtente((String)spesaSingola.get("idUtente"));
    }
}

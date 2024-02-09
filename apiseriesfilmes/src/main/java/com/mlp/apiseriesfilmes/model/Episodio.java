package com.mlp.apiseriesfilmes.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {

    private Integer temporada;
    private  String titulo;
    private Integer numeroEpisodio;
    private Double avaliacao;
    private LocalDate dataLancamento;

    public Episodio(Integer numeroTemporada, DadosEpisodio dadosEpisodio) {
        this.temporada = numeroTemporada;
        this.titulo = dadosEpisodio.titulo();
        this.numeroEpisodio = dadosEpisodio.numeroDeEpisodios();
        try {
            this.avaliacao = Double.valueOf(dadosEpisodio.avaliacao());
        } catch (NumberFormatException ex) {
            this.avaliacao = 0.0;
        }
        try {
            this.dataLancamento = LocalDate.parse(dadosEpisodio.dataLancamento());
        } catch (DateTimeParseException ex) {
            this.dataLancamento = null;
        }

    }

    public Integer getTemporada() { return temporada; }
    public void setTemporada(Integer temporada) { this.temporada = temporada; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public Integer getNumeroEpisodio() { return numeroEpisodio; }
    public void setNumeroEpisodio(Integer numeroEpisodio) { this.numeroEpisodio = numeroEpisodio; }
    public Double getAvliacao() { return avaliacao; }
    public void setAvliacao(Double avliacao) { this.avaliacao = avliacao; }
    public LocalDate getDataLancamento() { return dataLancamento; }
    public void setDataLancamento(LocalDate dataAvaliacao) { this.dataLancamento = dataAvaliacao; }

    @Override
    public String toString() {
        return "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", numeroEpisodio=" + numeroEpisodio +
                ", avaliacao=" + avaliacao +
                ", dataLancamento=" + dataLancamento;
    }
}

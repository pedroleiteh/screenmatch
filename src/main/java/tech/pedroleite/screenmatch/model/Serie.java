package tech.pedroleite.screenmatch.model;

import java.util.OptionalDouble;

import tech.pedroleite.screenmatch.dtos.SerieDto;

public class Serie {
    private String titulo;
    private Integer totalTemporadas;
    private Double avaliacao;
    private Categoria genero;
    private String atores;
    private String poster;
    private String sinopse;

    public Serie(SerieDto serieDto) {
        this.titulo = serieDto.titulo();
        this.totalTemporadas = serieDto.totalTemporadas();
        this.avaliacao = OptionalDouble.of(Double.valueOf(serieDto.avaliacao())).orElse(0);
        this.genero = Categoria.fromString(serieDto.genero().split(",")[0].trim());
        this.atores = serieDto.atores();
        this.poster = serieDto.poster();
        this.sinopse = serieDto.sinopse();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    @Override
    public String toString() {
        return "Serie [titulo=" + titulo + ", totalTemporadas=" + totalTemporadas + ", avaliacao=" + avaliacao
                + ", genero=" + genero + ", atores=" + atores + ", poster=" + poster + ", sinopse=" + sinopse + "]";
    }
}

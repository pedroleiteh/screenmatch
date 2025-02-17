package tech.pedroleite.screenmatch.model;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import tech.pedroleite.screenmatch.dtos.SerieDto;

@Entity
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Integer totalTemporadas;
    private Double avaliacao;
    @Enumerated(EnumType.STRING)
    private Categoria genero;
    private String atores;
    private String poster;
    private String sinopse;
    
    @Transient
    private List<Episodio> episodios = new ArrayList<>(); 

    public Serie() {}

    public Serie(SerieDto serieDto) {
        this.titulo = serieDto.titulo();
        this.totalTemporadas = serieDto.totalTemporadas();
        this.avaliacao = OptionalDouble.of(Double.valueOf(serieDto.avaliacao())).orElse(0);
        this.genero = Categoria.fromString(serieDto.genero().split(",")[0].trim());
        this.atores = serieDto.atores();
        this.poster = serieDto.poster();
        this.sinopse = serieDto.sinopse();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        this.episodios = episodios;
    }

    @Override
    public String toString() {
        return "Serie [titulo=" + titulo + ", totalTemporadas=" + totalTemporadas + ", avaliacao=" + avaliacao
                + ", genero=" + genero + ", atores=" + atores + ", poster=" + poster + ", sinopse=" + sinopse + "]";
    }
}

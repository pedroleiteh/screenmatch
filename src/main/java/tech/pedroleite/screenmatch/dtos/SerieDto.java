package tech.pedroleite.screenmatch.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SerieDto( @JsonAlias("Title") String titulo,
                        @JsonAlias("totalSeasons") Integer totalTemporadas,
                        @JsonAlias("imdbRating") String avaliacao,
                        @JsonAlias("Genre") String genero,
                        @JsonAlias("Actors") String atores,
                        @JsonAlias("Poster") String poster,
                        @JsonAlias("Plot") String sinopse) {
    
}

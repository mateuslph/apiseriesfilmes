package com.mlp.apiseriesfilmes.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(@JsonAlias("Title")String titulo,
                            @JsonAlias("Episode")Integer numeroDeEpisodios,
                            @JsonAlias("imdbRating")String avaliacao,
                            @JsonAlias("Released")String dataLancamento) {
}

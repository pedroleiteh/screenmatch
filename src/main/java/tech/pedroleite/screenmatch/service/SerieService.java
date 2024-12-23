package tech.pedroleite.screenmatch.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tech.pedroleite.screenmatch.dtos.SerieDto;
import tech.pedroleite.screenmatch.dtos.TemporadaDto;

public class SerieService {
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=" + System.getenv("OMDB_KEY");

    public String pesquisarSerie(Scanner sc) {
        System.out.print("\033[H\033[2J");
        System.out.println("Digite o nome da série para busca:");
        String nomeSerie = sc.nextLine();

        return nomeSerie;
    }

    public void exibirDadosSerie(String nomeSerie) {
            var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
            var serie = converteDados.obterDados(json, SerieDto.class);
            System.out.println(serie);
    }

    public void exibirEpisodiosSerie(String nomeSerie) {
        var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        var serie = converteDados.obterDados(json, SerieDto.class);
        List<TemporadaDto> temporadas = new ArrayList<>();
        
        for (int i = 1; i < serie.totalTemporadas(); i++) {
            json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i +API_KEY);
            TemporadaDto temporadaDto = converteDados.obterDados(json, TemporadaDto.class);
            temporadas.add(temporadaDto);
        }
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
    }
}

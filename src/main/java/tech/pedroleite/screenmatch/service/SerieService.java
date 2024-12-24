package tech.pedroleite.screenmatch.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import tech.pedroleite.screenmatch.dtos.EpisodioDto;
import tech.pedroleite.screenmatch.dtos.SerieDto;
import tech.pedroleite.screenmatch.dtos.TemporadaDto;
import tech.pedroleite.screenmatch.model.Episodio;

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
        
        List<Episodio> episodios = temporadas.stream()
        .flatMap(t -> t.episodios().stream()
                .map(e -> new Episodio(t.numero(), e))
        ).collect(Collectors.toList());

        episodios.forEach(System.out::println);
    }

    public void top5melhoresSeries(String nomeSerie) {
        var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        var serie = converteDados.obterDados(json, SerieDto.class);

        List<TemporadaDto> temporadas = new ArrayList<>();
        
        for (int i = 1; i < serie.totalTemporadas(); i++) {
            json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i +API_KEY);
            TemporadaDto temporadaDto = converteDados.obterDados(json, TemporadaDto.class);
            temporadas.add(temporadaDto);
        }

        List<EpisodioDto> episodioDtos = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        System.out.println("\nTop 5 episodios");
        episodioDtos.stream()
            .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
            .sorted(Comparator.comparing(EpisodioDto::avaliacao).reversed())
            .limit(5)
            .forEach(System.out::println);
    }
}

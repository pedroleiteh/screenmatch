package tech.pedroleite.screenmatch.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.pedroleite.screenmatch.dtos.EpisodioDto;
import tech.pedroleite.screenmatch.dtos.SerieDto;
import tech.pedroleite.screenmatch.dtos.TemporadaDto;
import tech.pedroleite.screenmatch.model.Episodio;
import tech.pedroleite.screenmatch.model.Serie;
import tech.pedroleite.screenmatch.repository.SerieRepository;

@Service
public class SerieService {
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=" + System.getenv("OMDB_KEY");

    @Autowired
    private SerieRepository repository;

    public List<Serie> exibirSeries() {
        return repository.findAll();
    }

    public List<Serie> top5melhoresSeries() {
        return repository.findTop5ByOrderByAvaliacaoDesc();
    }

    public Serie exibirSeriePorId(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()) {
            return serie.get();
        }
        return null;
    }

    public String pesquisarSerie(Scanner sc) {
        System.out.print("\033[H\033[2J");
        System.out.println("Digite o nome da série para busca:");
        String nomeSerie = sc.nextLine();

        return nomeSerie;
    }

    public SerieDto buscarDadosSerie(String nomeSerie) {
        var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        return converteDados.obterDados(json, SerieDto.class);
    }

    public void exibirEpisodiosSerie(String nomeSerie) {
        String json;
        var serie = buscarDadosSerie(nomeSerie);
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

    public void top5melhoresEpsPorSeries(String nomeSerie) {
        String json;
        var serie = buscarDadosSerie(nomeSerie);

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

    public void buscandoEpsPorAno(String nomeSerie, int ano) {
        String json;
        var serie = buscarDadosSerie(nomeSerie);
        List<TemporadaDto> temporadas = new ArrayList<>();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate dataBusca = LocalDate.of(ano, 1, 1);

        for (int i = 1; i < serie.totalTemporadas(); i++) {
            json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i +API_KEY);
            TemporadaDto temporadaDto = converteDados.obterDados(json, TemporadaDto.class);
            temporadas.add(temporadaDto);
        }
        
        List<Episodio> episodios = temporadas.stream()
        .flatMap(t -> t.episodios().stream()
                .map(e -> new Episodio(t.numero(), e))
        ).collect(Collectors.toList());

        episodios.stream()
            .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
            .forEach(e -> System.out.println(
                "Temporada: " + e.getTemporada() +
                " Episodio: " + e.getTitulo() +
                " Data lançamento: " + e.getDataLancamento().format(df)
            ));
        
    }
}

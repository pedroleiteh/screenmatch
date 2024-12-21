package tech.pedroleite.screenmatch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tech.pedroleite.screenmatch.dtos.EpisodioDto;
import tech.pedroleite.screenmatch.dtos.SerieDto;
import tech.pedroleite.screenmatch.dtos.TemporadaDto;
import tech.pedroleite.screenmatch.service.ConsumoApi;
import tech.pedroleite.screenmatch.service.ConverteDados;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		ConsumoApi consumoApi = new ConsumoApi();
		ConverteDados conversor = new ConverteDados();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=peaky+blinders&apikey=1d86c7af");
		
		SerieDto dados = conversor.obterDados(json, SerieDto.class);
		System.out.println(dados);

		json = consumoApi.obterDados("https://www.omdbapi.com/?t=suits&&season=1&episode=4&apikey=1d86c7af");

		EpisodioDto dadosEp = conversor.obterDados(json, EpisodioDto.class);
		System.out.println(dadosEp);

		List<TemporadaDto> temporadas = new ArrayList<>();

		for (int i = 1; i <= dados.totalTemporadas(); i++) {
			json = consumoApi.obterDados("https://www.omdbapi.com/?t=suits&&season=" + i + "&apikey=1d86c7af");
			TemporadaDto temporadaDto = conversor.obterDados(json, TemporadaDto.class);
			temporadas.add(temporadaDto);
		}
		temporadas.forEach(System.out::println);
		
	}
}

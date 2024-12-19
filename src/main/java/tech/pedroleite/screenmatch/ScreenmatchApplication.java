package tech.pedroleite.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tech.pedroleite.screenmatch.dtos.SerieDto;
import tech.pedroleite.screenmatch.service.ConsumoApi;
import tech.pedroleite.screenmatch.service.ConverteDados;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConsumoApi consumoApi = new ConsumoApi();
		ConverteDados conversor = new ConverteDados();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=suits&apikey=1d86c7af");
		
		SerieDto dados = conversor.obterDados(json, SerieDto.class);
		System.out.println(dados);
		
	}
}

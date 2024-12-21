package tech.pedroleite.screenmatch.ui;

import java.util.Scanner;

import tech.pedroleite.screenmatch.dtos.SerieDto;
import tech.pedroleite.screenmatch.service.ConsumoApi;
import tech.pedroleite.screenmatch.service.ConverteDados;

public class Ui {

    private Scanner sc = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=" + System.getenv("OMDB_KEY");


    public void exibiMenu() {
        System.out.println("Digite o nome da série para busca: ");
        var nomeSerie = sc.nextLine();
        var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        var serie = converteDados.obterDados(json, SerieDto.class);
        System.out.println(serie);
    }
}
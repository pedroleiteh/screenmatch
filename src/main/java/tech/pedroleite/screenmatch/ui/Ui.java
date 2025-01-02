package tech.pedroleite.screenmatch.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tech.pedroleite.screenmatch.model.Serie;
import tech.pedroleite.screenmatch.service.SerieService;

public class Ui {

    private Scanner sc = new Scanner(System.in);
    private SerieService serieService = new SerieService();
    private List<Serie> series = new ArrayList<>();


    public void exibiMenu() {

        var nomeSerie = serieService.pesquisarSerie(sc);
        var serieDto = serieService.buscarDadosSerie(nomeSerie);
        System.out.println(serieDto);
        series.add(new Serie(serieDto));

        while (true) {
            System.out.println("\nEscolha uma opção: ");
            System.out.println("Digite 1 para mostrar as temporadas da serie atual");
            System.out.println("Digite 2 para pesquisar outra série");
            System.out.println("Digite 3 para mostrar os 5 melhores eps da serie atual");
            System.out.println("Digite 4 para pesquisar episódios por data");
            System.out.println("Digite 5 para listar todas a series buscadas");
            System.out.println("Digite 'sair' para sair");
            var resposta = sc.nextLine();

            if (resposta.trim().toUpperCase().equals("SAIR")) {
                break;
            }

            switch (resposta) {
                case "1":
                    serieService.exibirEpisodiosSerie(nomeSerie);
                    break;
                case "2":
                    nomeSerie = serieService.pesquisarSerie(sc);
                    serieDto = serieService.buscarDadosSerie(nomeSerie);
                    System.out.println(serieDto);
                    series.add(new Serie(serieDto));
                    break;
                case "3":
                    serieService.top5melhoresSeries(nomeSerie);
                    break;
                case "4":
                    System.out.println("A partir de que ano você deseja ver os episódios");
                    var ano = sc.nextInt();
                    sc.nextLine();
                    serieService.buscandoEpsPorAno(nomeSerie, ano);
                case "5":
                    System.out.println(series);
                default:
                    break;
            }
        }
    }
}
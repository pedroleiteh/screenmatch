package tech.pedroleite.screenmatch.ui;

import java.util.Scanner;


import tech.pedroleite.screenmatch.service.SerieService;

public class Ui {

    private Scanner sc = new Scanner(System.in);
    private SerieService serieService = new SerieService();


    public void exibiMenu() {

        var nomeSerie = serieService.pesquisarSerie(sc);
        serieService.exibirDadosSerie(nomeSerie);

        while (true) {
            System.out.println("Escolha uma opção: ");
            System.out.println("Digite 1 para mostrar as temporadas da serie atual");
            System.out.println("Digite 2 para pesquisar outra série");
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
                    serieService.exibirDadosSerie(nomeSerie);
                    break;
                default:
                    break;
            }
        }
    }
}
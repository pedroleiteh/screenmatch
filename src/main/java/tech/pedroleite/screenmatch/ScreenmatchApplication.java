package tech.pedroleite.screenmatch;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tech.pedroleite.screenmatch.repository.SerieRepository;
import tech.pedroleite.screenmatch.ui.Ui;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner{

	@Autowired
	private SerieRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Ui ui = new Ui(repository);
		ui.exibiMenu();
	}
}

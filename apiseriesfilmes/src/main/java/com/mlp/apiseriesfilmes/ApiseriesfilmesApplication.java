package com.mlp.apiseriesfilmes;

import com.mlp.apiseriesfilmes.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiseriesfilmesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiseriesfilmesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal();
		principal.exibeMenu();

//		List<DadosTemporada> temporadas = new ArrayList<>();
//		for (int i = 1; i<=dados.totalTemporadas(); i++) {
//			json = consumoApi.obterDadosDaApi("https://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=f7a78d0d");
//			DadosTemporada dadosTemporada = conversor.obterDadosDoBody(json, DadosTemporada.class);
//			temporadas.add(dadosTemporada);
//		}
//		temporadas.forEach(System.out::println);
	}
}

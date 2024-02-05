package com.mlp.apiseriesfilmes;

import com.mlp.apiseriesfilmes.model.DadosEpisodio;
import com.mlp.apiseriesfilmes.model.DadosSerie;
import com.mlp.apiseriesfilmes.service.ConsumoApi;
import com.mlp.apiseriesfilmes.service.ConverteDados;
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
//		System.out.println("Primeiro projeto Spring sem Web");
		ConsumoApi consumoApi = new ConsumoApi();
		var json = consumoApi.obterDadosDaApi("https://www.omdbapi.com/?t=gilmore+girls=&apikey=f7a78d0d");
		// vai retornar uma String formatada no padrao Json da API
		System.out.println(json);
//		json = consumoApi.obterDados("https://coffee.alexflipnote.dev/random.json");
//		System.out.println(json);

		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDadosDoBody(json, DadosSerie.class);
		// vai retornar uma classe mapeada pelo metodo readValue() da classe nativa ObjectMapper
		System.out.println(dados);

		json = consumoApi.obterDadosDaApi("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=f7a78d0d");
		DadosEpisodio dadosEpisodio = conversor.obterDadosDoBody(json, DadosEpisodio.class);
		System.out.println(dadosEpisodio);
	}
}

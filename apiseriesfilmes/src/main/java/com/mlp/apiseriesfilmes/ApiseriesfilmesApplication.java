package com.mlp.apiseriesfilmes;

import com.mlp.apiseriesfilmes.service.ConsumoApi;
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
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=matrix=&apikey=f7a78d0d");
		System.out.println(json);
		json = consumoApi.obterDados("https://coffee.alexflipnote.dev/random.json");
		System.out.println(json);
	}
}

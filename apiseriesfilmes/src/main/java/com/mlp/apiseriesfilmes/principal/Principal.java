package com.mlp.apiseriesfilmes.principal;

import com.mlp.apiseriesfilmes.model.DadosEpisodio;
import com.mlp.apiseriesfilmes.model.DadosSerie;
import com.mlp.apiseriesfilmes.model.DadosTemporada;
import com.mlp.apiseriesfilmes.service.ConsumoApi;
import com.mlp.apiseriesfilmes.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner sc = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=f7a78d0d";

    public void exibeMenu() {
        System.out.println("Digite o nome da serie para busca...");
        var nomeSerie = sc.nextLine();
        var json = consumoApi.obterDadosDaApi(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDadosDoBody(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();
		for (int i = 1; i<=dados.totalTemporadas(); i++) {
			json = consumoApi.obterDadosDaApi(ENDERECO + nomeSerie.replace(" ", "+")
                    + "&season=" + i + API_KEY);
			DadosTemporada dadosTemporada = conversor.obterDadosDoBody(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println);

//        for (int i = 0; i < dados.totalTemporadas(); i++) {
//            List<DadosEpisodio> episodioTemporada = temporadas.get(i).episodios();
//            for (int j = 0; j < episodioTemporada.size(); j++) {
//                System.out.println(episodioTemporada.get(j).titulo());
//            }
//        }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
    }
}

package com.mlp.apiseriesfilmes.principal;

import com.mlp.apiseriesfilmes.model.DadosEpisodio;
import com.mlp.apiseriesfilmes.model.DadosSerie;
import com.mlp.apiseriesfilmes.model.DadosTemporada;
import com.mlp.apiseriesfilmes.model.Episodio;
import com.mlp.apiseriesfilmes.service.ConsumoApi;
import com.mlp.apiseriesfilmes.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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

//        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
//                .collect(Collectors.toList()); // gera uma lista imutavel
                .toList();  // pode modificar a lista

//        System.out.println("\nTop 10 episódios:");
//        dadosEpisodios.stream()
//                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
//                .peek(e -> System.out.println("Primeiro filtro (N/A) " + e))
//                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
////                .peek(e -> System.out.println("Ordenado" + e))
//                .limit(10)
//                .map(e -> e.titulo().toUpperCase())
//                .forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numeroEpisodio(), d))
                ).collect(Collectors.toList());

//        episodios.forEach(System.out::println);

//        System.out.println("Digite um trecho do título do episódio...");
//        var trechoDoTitulo = sc.nextLine();
//        Optional<Episodio> episodioBuscado = episodios.stream()
//                .filter(e -> e.getTitulo().toUpperCase().contains(trechoDoTitulo.toUpperCase()))
//                .findFirst();
//        if (episodioBuscado.isPresent()) {
//            System.out.println("Episódio encontrado:");
//            System.out.println("Temporada: " + episodioBuscado.get());
//        } else {
//            System.out.println("Episódio não encontrado!");
//        }

//        System.out.println("A partir de que ano você quer ver os episódios?");
//        var ano = sc.nextInt();
//        sc.nextLine();
//        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
//        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        episodios.stream()
//                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getTemporada() +
//                                " Episodio: " + e.getTitulo() +
//                                " Data de Lançamento: " + e.getDataLancamento().format(formatador)
//                ));

        Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
                .filter(e -> e.getAvliacao() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getAvliacao)));
        System.out.println(avaliacoesPorTemporada);

        DoubleSummaryStatistics est = episodios.stream()
                .filter(e -> e.getAvliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getAvliacao));
        System.out.println("Média: " + est.getAverage());
        System.out.println("Melhor episódio: " + est.getMax());
        System.out.println("Pior episódio: " + est.getMin());
        System.out.println("quantidade: " + est.getCount());

    }
}

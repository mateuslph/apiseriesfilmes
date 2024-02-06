package com.mlp.apiseriesfilmes.principal;

import com.mlp.apiseriesfilmes.model.DadosSerie;
import com.mlp.apiseriesfilmes.service.ConsumoApi;
import com.mlp.apiseriesfilmes.service.ConverteDados;

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
    }
}

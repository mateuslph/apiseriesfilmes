package com.mlp.apiseriesfilmes.service;

public interface IConverteDados {
    <T> T obterDadosDoBody(String json, Class<T> classe);
}

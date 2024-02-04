package com.mlp.apiseriesfilmes.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}

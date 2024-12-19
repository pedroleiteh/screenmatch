package tech.pedroleite.screenmatch.service;

public interface IConverteDados {
    
    <T> T obterDados(String json, Class<T> classe);
}

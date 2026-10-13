package br.edu.model;

public class ResultadoVerificacao {
    private final int statusCode;
    private final int quantidadeEncontrada;
    
    public ResultadoVerificacao(int statusCode, int quantidadeEncontrada){
        this.statusCode = statusCode;
        this.quantidadeEncontrada = quantidadeEncontrada;
    }
    
    public int getStatusCode() {
        return statusCode;
    }

    public int getQuantidadeEncontrada() {
        return quantidadeEncontrada;
    }
}

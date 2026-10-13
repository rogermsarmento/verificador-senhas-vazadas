package br.edu.ui;

import java.util.Scanner;

public class ConsoleUI {
    
    public void exibirResultado(int quantidadeEncontrada) {
        if (quantidadeEncontrada > 0) {
            System.out.println();
            System.out.println("ATENÇÃO: Senha Encontrada em Vazamentos Conhecidos.");
            System.out.println();
            System.out.println("Quantidade de ocorrências: "+ quantidadeEncontrada);
        } else {
            System.out.println();
            System.out.println("Senha não encontrada nos vazamentos consultados.");
        }
    }
    
    public String lerSenha(Scanner sc) {
        String senha;
        do {
            System.out.print("Digite uma senha: ");
            senha = sc.nextLine();

            if (senha.isBlank()) {
                System.out.println("A senha não pode ser vazia. Tente novamente.");
                System.out.println();
            }
        } while (senha.isBlank());
        return senha;
    }
    
    public void exibirCabecalho() {
        System.out.println("==============================");
        System.out.println("   VERIFICADOR DE SENHAS");
        System.out.println("==============================");
        System.out.println();
    }
    
    public void exibirStatusHttp(int statusCode){
        switch (statusCode) {
            case 200 -> System.out.println("HTTP 200 - OK: requisição realizada com sucesso.");
            case 400 -> System.out.println("HTTP 400 - Bad Request: requisição inválida.");
            case 404 -> System.out.println("HTTP 404 - Not Found: recurso não encontrado.");
            case 429 -> System.out.println("HTTP 429 - Too Many Requests: muitas requisições.");
            case 500 -> System.out.println("HTTP 500 - Internal Server Error: erro interno do servidor.");
            default -> System.out.println("HTTP " + statusCode + " - erro na requisição.");
        }
    }
    
    public void exibirConsultando() {
        System.out.println();
        System.out.println("Consultando base de vazamentos...");
    }
    
    public void exibirErroHash() {
        System.out.println();
        System.out.println("Não foi possível gerar o hash da senha.");
    }
    
    public void exibirErroConsulta() {
        System.out.println();
        System.out.println("Não foi possível consultar a base de vazamentos.");
    }
    
    public void exibirConsultaInterrompida() {
        System.out.println();
        System.out.println("A consulta foi interrompida.");
    }
    
}

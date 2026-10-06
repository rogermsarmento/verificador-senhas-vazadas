package br.edu.principal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        
        try(Scanner sc = new Scanner(System.in)) {
            String senha;
            
            exibirCabecalho();
            
            senha = lerSenha(sc);
            
            System.out.println();
            System.out.println("Consultando base de vazamentos...");
            System.out.println();
            
            try{
                String hash = gerarHash(senha);
                
                String prefixo = obterPrefixo(hash);
                String sufixo = obterSufixo(hash);
                
                HttpResponse<String> response = consultarApi(prefixo);
                
                int statusCode = response.statusCode();
                
                exibirStatusHttp(statusCode);
                
                if (statusCode == 200) {
                    String corpoResposta = response.body();
                    int quantidadeEncontrada = buscarQuantidade(corpoResposta, sufixo);
                    exibirResultado(quantidadeEncontrada);
                }
                else {
                    System.out.println();
                    System.out.println("Erro ao consultar a base de vazamentos.");
                }
            }
            catch (NoSuchAlgorithmException e) {
                System.out.println();
                System.out.println("Não foi possível gerar o hash da senha.");
            }
            catch (IOException e) {
                System.out.println();
                System.out.println("Não foi possível consultar a base de vazamentos.");
            }
            catch (InterruptedException e) {
                System.out.println();
                System.out.println("A consulta foi interrompida.");
            }
        }
    }
    
    public static String obterPrefixo(String hash) {
        return hash.substring(0, 5);
    }
    
    public static String obterSufixo(String hash) {
        return hash.substring(5);
    }
    
    public static String gerarHash(String senha) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-1");
                
        byte[] hashBytes = md.digest(senha.getBytes(StandardCharsets.UTF_8));

        StringBuilder hashHex = new StringBuilder();

        for (byte b : hashBytes) hashHex.append(String.format("%02X", b));

        return hashHex.toString();
    }
    
    static HttpResponse<String> consultarApi(String prefixo) throws IOException, InterruptedException {

        String url = "https://api.pwnedpasswords.com/range/" + prefixo;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        return client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );
    }
    
    public static void exibirStatusHttp(int statusCode){
        switch (statusCode) {
            case 200 -> System.out.println("HTTP 200 - OK: requisição realizada com sucesso.");
            case 400 -> System.out.println("HTTP 400 - Bad Request: requisição inválida.");
            case 404 -> System.out.println("HTTP 404 - Not Found: recurso não encontrado.");
            case 429 -> System.out.println("HTTP 429 - Too Many Requests: muitas requisições.");
            case 500 -> System.out.println("HTTP 500 - Internal Server Error: erro interno do servidor.");
            default -> System.out.println("HTTP " + statusCode + " - erro na requisição.");
        }
    }
    
    static int buscarQuantidade(String corpoResposta, String sufixo) {
        String[] linhas = corpoResposta.split("\\R");

        for (String linha : linhas) {

            String[] partes = linha.split(":");

            String sufixoRetornado = partes[0];
            int quantidade = Integer.parseInt(partes[1]);

            if (sufixo.equals(sufixoRetornado)) return quantidade;
        }
        return 0;
    }
    
    static void exibirResultado(int quantidadeEncontrada) {
        if (quantidadeEncontrada > 0) {
            System.out.println();
            System.out.println("ATENÇÃO: senha encontrada em vazamentos conhecidos.");
            System.out.println("Quantidade de ocorrências: "+ quantidadeEncontrada);
        } else {
            System.out.println();
            System.out.println("Senha não encontrada nos vazamentos consultados.");
        }
    }
    
    static String lerSenha(Scanner sc) {
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
    
    static void exibirCabecalho() {
        System.out.println("==============================");
        System.out.println("   VERIFICADOR DE SENHAS");
        System.out.println("==============================");
        System.out.println();
    }
    
}


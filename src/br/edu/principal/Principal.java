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
            System.out.println("==============================");
            System.out.println("   VERIFICADOR DE SENHAS");
            System.out.println("==============================");
            System.out.println();
            do {
                System.out.print("Digite uma senha: ");
                senha = sc.nextLine();
                
                if (senha.isBlank()) {
                    System.out.println("A senha não pode ser vazia. Tente novamente.");
                    System.out.println();
                }
            } while (senha.isBlank());
            System.out.println();
            System.out.println("Consultando base de vazamentos...");
            try{
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                
                byte[] hashBytes = md.digest(senha.getBytes(StandardCharsets.UTF_8));
                
                StringBuilder hashHex = new StringBuilder();
                
                for (byte b : hashBytes) hashHex.append(String.format("%02X", b));
                
                String hash = hashHex.toString();
                String prefixo = hash.substring(0, 5);
                String sufixo = hash.substring(5);
                
                String url = "https://api.pwnedpasswords.com/range/" + prefixo;
                //String url = "https://api-inexistente.pwnedpasswords.com/range/" + prefixo; // String para teste
                
                HttpClient client = HttpClient.newHttpClient();
                
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
                
                HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
                
                int statusCode = response.statusCode();
                
                //System.out.println();
                //System.out.println("SHA-1 completo: " + hash);
                //System.out.println("Prefixo: " + prefixo);
                //System.out.println("Sufixo: " + sufixo);
                //System.out.println("URL: " + url);
                //System.out.println();
                //System.out.println("Status HTTP: " + response.statusCode());
                
                if (statusCode == 200) {
                    
                    System.out.println();
                    System.out.println("HTTP 200 - OK: requisição realizada com sucesso.");
                    String corpoResposta = response.body();
                
                    String[] linhas = corpoResposta.split("\\R");//divide uma string com base em qualquer quebra de linha universal

                    //System.out.println();
                    //System.out.println("Quantidade de linhas recebidas: " + linhas.length);

                    boolean encontrado = false;
                    int quantidadeEncontrada = 0;

                    for (String linha : linhas) {
                        String[] partes = linha.split(":");

                        String sufixoRetornado = partes[0];
                        int quantidade = Integer.parseInt(partes[1]);

                        if (sufixo.equals(sufixoRetornado)) {
                            encontrado = true;
                            quantidadeEncontrada = quantidade;
                            break;
                        }
                    }
                    if (encontrado) {
                        System.out.println();
                        System.out.println("ATENÇÃO: Senha encontrada em vazamentos conhecidos.");
                        System.out.println("Quantidade de ocorrencias: " + quantidadeEncontrada);
                    } else {
                        System.out.println();
                        System.out.println("Senha não encontrada nos vazamentos consultados.");
                        //System.out.println("ATENÇÃO: Isso não siguinifica que a senha é segura!");
                    }
                }
                else {
                    //System.out.println();
                    //System.out.println("Não foi possível consultar a base de vazamentos.");
                    //System.out.println("Código HTTP: " + statusCode);
                    
                    System.out.println();
                    System.out.println("Erro ao consultar a base de vazamentos.");
                    
                    switch (statusCode) {
                        case 400 -> System.out.println("HTTP 400 - Bad Request: requisição inválida.");
                        case 404 -> System.out.println("HTTP 404 - Not Found: recurso não encontrado.");
                        case 429 -> System.out.println("HTTP 429 - Too Many Requests: muitas requisições.");
                        case 500 -> System.out.println("HTTP 500 - Internal Server Error: erro interno do servidor.");
                        default -> System.out.println("HTTP " + statusCode + " - erro na requisição.");
                    }
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
}
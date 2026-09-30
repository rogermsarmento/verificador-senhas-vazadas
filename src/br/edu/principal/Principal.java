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

    public static void main(String[] args)
            throws NoSuchAlgorithmException, IOException, InterruptedException {

        Scanner sc = new Scanner(System.in);

        String senha;

        System.out.println("==============================");
        System.out.println("   VERIFICADOR DE SENHAS");
        System.out.println("==============================");
        System.out.println();

        System.out.print("Digite uma senha: ");
        senha = sc.nextLine();

        MessageDigest md = MessageDigest.getInstance("SHA-1");

        byte[] hashBytes = md.digest(
                senha.getBytes(StandardCharsets.UTF_8)
        );

        StringBuilder hashHex = new StringBuilder();

        for (byte b : hashBytes) {
            hashHex.append(String.format("%02X", b));
        }

        String hash = hashHex.toString();

        String prefixo = hash.substring(0, 5);
        String sufixo = hash.substring(5);

        String url = "https://api.pwnedpasswords.com/range/" + prefixo;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );
        System.out.println();
        System.out.println("SHA-1 completo: " + hash);
        System.out.println("Prefixo: " + prefixo);
        System.out.println("Sufixo: " + sufixo);
        System.out.println("URL: " + url);

        System.out.println();
        System.out.println("Status HTTP: " + response.statusCode());

        String corpoResposta = response.body();

        String[] linhas = corpoResposta.split("\\R");

        System.out.println();
        System.out.println("Quantidade de linhas recebidas: " + linhas.length);

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
            System.out.println("ATENÇÃO: senha encontrada em vazamentos conhecidos.");
            System.out.println("Quantidade de ocorrências: " + quantidadeEncontrada);
        } else {
            System.out.println();
            System.out.println("Senha não encontrada nos vazamentos consultados.");
            System.out.println("ATENÇÃO: Isso não siguinifica que a senha é segura!");
        }
        sc.close();
    }
}
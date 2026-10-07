package br.edu.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PwnedPasswordService {
    
    private final String urlBase;
    
    public PwnedPasswordService() {
        urlBase = "https://api.pwnedpasswords.com/range/";
    }
    
    public PwnedPasswordService(String urlBase) {
        this.urlBase = urlBase;
    }
    
    public HttpResponse<String> consultarApi(String prefixo) throws IOException, InterruptedException {

    String url = urlBase + prefixo;

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

    public int buscarQuantidade(String corpoResposta, String sufixo) {
    String[] linhas = corpoResposta.split("\\R");

    for (String linha : linhas) {

        String[] partes = linha.split(":");

        String sufixoRetornado = partes[0];
        int quantidade = Integer.parseInt(partes[1]);

        if (sufixo.equals(sufixoRetornado)) return quantidade;
    }
    return 0;
}

}

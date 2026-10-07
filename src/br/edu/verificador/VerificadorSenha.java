package br.edu.verificador;

import static br.edu.principal.Principal.exibirStatusHttp;
import br.edu.service.HashService;
import br.edu.service.PwnedPasswordService;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.net.http.HttpResponse;

public class VerificadorSenha {
    
    private final HashService hashService;
    private final PwnedPasswordService pwnedPasswordService;
    
    public VerificadorSenha(HashService hashService, PwnedPasswordService pwnedPasswordService) {
        this.hashService = hashService;
        this.pwnedPasswordService = pwnedPasswordService;
    }
    
    public int verificar(String senha) throws NoSuchAlgorithmException, IOException, InterruptedException {
        
        String hash = hashService.gerarHash(senha);
        String prefixo = hashService.obterPrefixo(hash);
        String sufixo = hashService.obterSufixo(hash);
        
        HttpResponse<String> response = pwnedPasswordService.consultarApi(prefixo);
        
        int statusCode = response.statusCode();
        
        exibirStatusHttp(statusCode);

        if (statusCode == 200) {

            String corpoResposta = response.body();
            return pwnedPasswordService.buscarQuantidade(corpoResposta, sufixo);
        }
        throw new IOException("Erro HTTP ao consultar a API: " + statusCode);
        //return 0;
    }
}

package br.edu.config;

import br.edu.service.HashService;
import br.edu.service.PwnedPasswordService;
import br.edu.verificador.VerificadorSenha;

public class AppFactory {
    
    public VerificadorSenha criarVerificadorSenha() {

        HashService hashService = new HashService();

        PwnedPasswordService pwnedPasswordService = new PwnedPasswordService("https://api.pwnedpasswords.com/range/");

        return new VerificadorSenha(hashService, pwnedPasswordService);
    }
}
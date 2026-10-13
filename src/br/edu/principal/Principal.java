package br.edu.principal;

import br.edu.service.PwnedPasswordService;
import br.edu.service.HashService;
import br.edu.model.ResultadoVerificacao;
import br.edu.ui.ConsoleUI;
import br.edu.verificador.VerificadorSenha;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        
        try(Scanner sc = new Scanner(System.in)) {
            
            String senha;
            
            HashService hashService = new HashService();
            
            PwnedPasswordService pwnedPasswordService = new PwnedPasswordService("https://api.pwnedpasswords.com/range/");
            
            VerificadorSenha verificador = new VerificadorSenha(hashService, pwnedPasswordService);
            
            ConsoleUI consoleUI = new ConsoleUI();
                        
            consoleUI.exibirCabecalho();
            
            senha = consoleUI.lerSenha(sc);
            
            consoleUI.exibirConsultando();
            
            try{
                ResultadoVerificacao resultado = verificador.verificar(senha);
                
                consoleUI.exibirStatusHttp(resultado.getStatusCode());
                
                consoleUI.exibirResultado(resultado.getQuantidadeEncontrada());
            }
            catch (NoSuchAlgorithmException e) {
                consoleUI.exibirErroHash();
            }
            catch (IOException e) {
                consoleUI.exibirErroConsulta();
            }
            catch (InterruptedException e) {
               consoleUI.exibirConsultaInterrompida();
            }
        }
    }
}
package br.edu.principal;

import br.edu.config.AppFactory;
import br.edu.model.ResultadoVerificacao;
import br.edu.ui.ConsoleUI;
import br.edu.verificador.VerificadorSenha;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        
        try(Scanner sc = new Scanner(System.in)) {
            
            AppFactory appFactory = new AppFactory();

            VerificadorSenha verificador = appFactory.criarVerificadorSenha();
            
            ConsoleUI consoleUI = new ConsoleUI();
                        
            consoleUI.exibirCabecalho();
            
            String senha = consoleUI.lerSenha(sc);
            
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
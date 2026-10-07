package br.edu.principal;

import br.edu.service.PwnedPasswordService;
import br.edu.service.HashService;
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
                        
            exibirCabecalho();
            
            senha = lerSenha(sc);
            
            System.out.println();
            System.out.println("Consultando base de vazamentos...");
            System.out.println();
            
            try{
                int quantidadeEncontrada = verificador.verificar(senha);
                exibirResultado(quantidadeEncontrada);
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
        
    public static void exibirResultado(int quantidadeEncontrada) {
        if (quantidadeEncontrada > 0) {
            System.out.println();
            System.out.println("ATENÇÃO: senha encontrada em vazamentos conhecidos.");
            System.out.println("Quantidade de ocorrências: "+ quantidadeEncontrada);
        } else {
            System.out.println();
            System.out.println("Senha não encontrada nos vazamentos consultados.");
        }
    }
    
    public static String lerSenha(Scanner sc) {
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
    
    public static void exibirCabecalho() {
        System.out.println("==============================");
        System.out.println("   VERIFICADOR DE SENHAS");
        System.out.println("==============================");
        System.out.println();
    }
    
}


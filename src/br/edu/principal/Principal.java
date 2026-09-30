package br.edu.principal;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String senha;
        
        System.out.println("==============================");
        System.out.println("   VERIFICADOR DE SENHAS");
        System.out.println("==============================");
        
        System.out.print("Digite uma senha: ");
        senha = sc.nextLine();

        System.out.println();
        System.out.println("Senha recebida: " + senha);

        sc.close();
    }
}

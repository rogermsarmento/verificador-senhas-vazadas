package br.edu.principal;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        Scanner sc = new Scanner(System.in);

        String senha;

        System.out.println("==============================");
        System.out.println("   VERIFICADOR DE SENHAS");
        System.out.println("==============================");
        System.out.println();

        System.out.print("Digite uma senha: ");
        senha = sc.nextLine();

        MessageDigest md = MessageDigest.getInstance("SHA-1");

        byte[] hashBytes = md.digest(senha.getBytes(StandardCharsets.UTF_8));

        StringBuilder hashHex = new StringBuilder();

        for (byte b : hashBytes) {
            hashHex.append(String.format("%02X", b));
            //System.out.println(b);
        }
        
        
        System.out.println();
        System.out.println("SHA-1: " + hashHex);

        sc.close();
    }
}
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
        }

        String hash = hashHex.toString();

        String prefixo = hash.substring(0, 5);
        String sufixo = hash.substring(5);

        System.out.println();
        System.out.println("SHA-1 completo: " + hash);
        System.out.println("Prefixo: " + prefixo);
        System.out.println("Sufixo: " + sufixo);

        sc.close();
    }
}
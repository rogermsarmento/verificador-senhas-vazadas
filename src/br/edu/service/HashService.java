package br.edu.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashService {
    
    public String gerarHash(String senha) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-1");
                
        byte[] hashBytes = md.digest(senha.getBytes(StandardCharsets.UTF_8));

        StringBuilder hashHex = new StringBuilder();

        for (byte b : hashBytes) hashHex.append(String.format("%02X", b));

        return hashHex.toString();
    } 
    
    public String obterPrefixo(String hash) {
        return hash.substring(0, 5);
    }
    
    public String obterSufixo(String hash) {
        return hash.substring(5);
    }
  
}

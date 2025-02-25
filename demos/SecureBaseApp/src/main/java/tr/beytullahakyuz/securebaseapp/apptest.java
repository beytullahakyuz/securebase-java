/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tr.beytullahakyuz.securebaseapp;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import tr.beytullahakyuz.securebase.Keccak;
import tr.beytullahakyuz.securebase.SecureBase;

/**
 *
 * @author beytullahakyuz
 */
public class apptest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
        SecureBase sb = new SecureBase(SecureBase.SBEncoding.UTF8);
        sb.SetSecretKey("xcompany-secret-key");

        byte[] filedatabytes = Files.readAllBytes(Paths.get("data.txt"));
        String filedata = new String(filedatabytes, StandardCharsets.UTF_8);
        
        //Encoding
        String encodeddata = sb.encode(filedata);
        Files.write(Paths.get("encoded.txt"), encodeddata.getBytes(StandardCharsets.UTF_8));

        //Decoding
        String decodeddata = sb.decode(encodeddata);
        Files.write(Paths.get("decoded.txt"), decodeddata.getBytes(StandardCharsets.UTF_8));
        
        System.out.println(computeHash("Keccak hashing algorithm test", 256));
        System.out.println(computeHash("Keccak hashing algorithm test", 512));
        /*
        System.out.println(computeHash("beytullah", 256));
        System.out.println(computeHash("beşiktaş çarşı grubu", 256));
        System.out.println(computeHash("beşiktaş çarşı grubu", 512));
        */
    }
    
    
    private static String computeHash(String s, int key) {
        Keccak keccak = new Keccak();
        byte[] input = s.getBytes(StandardCharsets.UTF_8);
        byte[] hash = keccak.hash(input, key);
        keccak.dispose();

        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

package general;

import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;
import java.nio.charset.*;
import java.nio.file.*;

public class EncryptOrDecrypt {
    public static void mainEncryptOrDecrypt(String[] args) throws IOException {
	
    	String s = FileSplit.class.getProtectionDomain().getCodeSource().getLocation().getPath().toString();
		String datafile = s.substring(0,s.length()-5)+"/res/test.jpg";
    	
    	File preEncrypt = new File(s.substring(0,s.length()-5)+"/res/test.jpg");
    	String content = readFile(preEncrypt, StandardCharsets.UTF_8);
    	//System.out.println("Input file contains: " + content);
    	File encrypted = new File(s.substring(0,s.length()-5)+"/res/test.encrypted");
    	File decrypted = new File(s.substring(0,s.length()-5)+"/res/test2.jpg");
    	
    	//For now, uncomment one of the following two lines to either encrypt or decrypt.
    	
    	//encrypt("default_key", preEncrypt, encrypted);
    	//decrypt("default_key", encrypted, decrypted);
    
    	//String content2 = readFile(h, StandardCharsets.UTF_8);
    	//System.out.println("Decrypted file contains: " + content2);
    }
    
    public static String readFile (File f, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(f.toPath());
        return new String(encoded, encoding);
    }

    public static void encrypt(String keystring, File inputFile, File outputFile) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            byte[] keybytes = sha.digest(keystring.getBytes("UTF-8"));
            keybytes = Arrays.copyOf(keybytes, 16);

            Key key = new SecretKeySpec(keybytes, "AES");
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, key);
            FileInputStream in = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            in.read(inputBytes);

            byte[] outputBytes = c.doFinal(inputBytes);
            FileOutputStream out = new FileOutputStream(outputFile);
            out.write(outputBytes);

            in.close();
            out.close();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Error encrypting");
        }

    }
  
    public static void decrypt(String keystring, File inputFile, File outputFile) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            byte[] keybytes = sha.digest(keystring.getBytes("UTF-8"));
            keybytes = Arrays.copyOf(keybytes, 16);

            Key key = new SecretKeySpec(keybytes, "AES");
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, key);
            FileInputStream in = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            in.read(inputBytes);

            byte[] outputBytes = c.doFinal(inputBytes);
            FileOutputStream out = new FileOutputStream(outputFile);
            out.write(outputBytes);

            in.close();
            out.close();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Error encrypting");
        }
    }
}
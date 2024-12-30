import java.io.*;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import java.util.Base64;

public class Blowfish {
    public static void main(String[] args) {
        try {
            
            KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
            keyGenerator.init(128);
            Key secretKey = keyGenerator.generateKey();
    
            Cipher cipherOut = Cipher.getInstance("Blowfish/CFB/NoPadding");
            cipherOut.init(Cipher.ENCRYPT_MODE, secretKey);
        
            byte[] iv = cipherOut.getIV();
            if (iv != null) {
                System.out.println("Initialization Vector: " + Base64.getEncoder().encodeToString(iv));
            }   

            FileInputStream fin = new FileInputStream("inputFile.txt");
            FileOutputStream fout = new FileOutputStream("outputFile.txt");
            CipherOutputStream cout = new CipherOutputStream(fout, cipherOut);

            int input;
            while ((input = fin.read()) != -1) {
                cout.write(input);
            }

            fin.close();
            cout.close();  
            
            System.out.println("File Encrypted Successfully!");
            
        } catch (FileNotFoundException e) {
            System.err.println("File not found. Ensure inputFile.txt exists.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
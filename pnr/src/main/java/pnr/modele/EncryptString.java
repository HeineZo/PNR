package pnr.modele;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Encrypt a character string
 */
public class EncryptString {
    private String encryptedPassword;
 
    /**
     * EncryptString constructor, starts the encryption method
     * 
     * @param password Character string to encrypt
     */
    public EncryptString(String password) {
        crypt(password);
    }

    /**
     * It takes a string, converts it to a byte array, creates a MessageDigest object, resets it,
     * updates it with the byte array, creates another MessageDigest object, digests the byte array,
     * creates a BigInteger object, and then converts the BigInteger to a string
     * 
     * @param password The password to be encrypted.
     */
    public void crypt(String password) {
        byte[] passwordBytes = password.getBytes();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(passwordBytes);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(passwordBytes);
            BigInteger number = new BigInteger(1, messageDigest);
            this.encryptedPassword = number.toString(16);
        } catch(NoSuchAlgorithmException e) {
            throw new Error("ERROR Passe: have not password", e);
        }
    }

    /**
     * This function returns the encrypted password of the user
     * 
     * @return The encrypted password.
     */
    public String getEncryptedPassword() {
        return this.encryptedPassword;
    }
}
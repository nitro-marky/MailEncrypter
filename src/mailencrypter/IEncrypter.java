/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package mailencrypter;


/**
 *
 * Interface implemented by the message encrypter.
 */
public interface IEncrypter {
    
    /**
     * 
     * @param message To be encrypted
     * @param key The key to encrypt with
     * @return String with encrypted message
     */
    String encryptMessage(String message, IEncryptionKey key);
    
}

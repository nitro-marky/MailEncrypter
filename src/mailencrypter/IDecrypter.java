/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package mailencrypter;

import Exceptions.InvalidDecryptionError;

/**
 *
 * Interface implemented by the message decrypter.
 */
public interface IDecrypter {
    
    /**
     * 
     * @param message To be decrypted
     * @param key The key for decryption
     * @return String with the decrypted message
     * @throws InvalidDecryptionError 
     */
     public String decryptMessage(String message, IEncryptionKey key)throws InvalidDecryptionError;
}

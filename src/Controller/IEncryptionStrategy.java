/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Controller;

import Exceptions.InvalidDecryptionError;

/**
 *
 * The IEncryptionStrategy gives the view access to the encryption related functions encrypt and decrypt.
 */
public interface IEncryptionStrategy extends IStrategy {
    
    /**
     * 
     * @param message The message to encrypt
     * @param id The ID of the key chosen to encrypt
     */
    void Encrypt(String message, int id);
    
    /**
     * 
     * @param message The message to decrypt
     * @param id The ID of the key chosen to decrypt
     * @throws InvalidDecryptionError 
     */
    void Decrypt(String message, int id)throws InvalidDecryptionError;
}

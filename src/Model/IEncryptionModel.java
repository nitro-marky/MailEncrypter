/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Model;

import Exceptions.InvalidDecryptionError;
import Exceptions.KeyFileException;
import View.IEncryptionObserver;
import java.util.List;

/**
 *
 * The interface implemented by the encryption model.
 */
public interface IEncryptionModel {
    
    /**
     * 
     * @param message The message to be decrypted
     * @param id The ID of the key needed to decrypt
     * @throws InvalidDecryptionError 
     */
    void DecryptMessage(String message, int id) throws InvalidDecryptionError;
    
    /**
     * 
     * @param message The message to encrypt
     * @param id The ID of the chosen encryption key
     */
    void EncryptMessage(String message, int id);
    
    /**
     * 
     * @param fileName The desired name and file path for the key file
     */
    void saveKeys(String fileName);
    
    /**
     * 
     */
    void GenerateKeys();
    
    /**
     * 
     * @param fileName Name and location of file to load
     * @throws KeyFileException 
     */
    void loadKeys(String fileName)throws KeyFileException; 
}

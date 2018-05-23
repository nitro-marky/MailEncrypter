/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Controller;

import Exceptions.KeyFileException;

/**
 *
 * The IKeyStrategy provides the view with encryption key related methods.
 */
public interface IKeyStrategy extends IStrategy{
    
    /**
     * 
     * @param fileName Name and location to save the file to
     */
    void saveKeys(String fileName);
    
    /**
     * 
     * @param filePath Name and location of the file to load
     * @throws KeyFileException 
     */
    void loadKeys(String filePath)throws KeyFileException;
    
}

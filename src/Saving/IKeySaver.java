/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Saving;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import mailencrypter.IEncryptionKey;

/**
 *
 * The interface implemented by the key saver class.
 */
public interface IKeySaver {
    
    /**
     * 
     * @param fileName The user entered file save name
     * @param keys List of encryption keys to be saved
     * @throws FileNotFoundException
     * @throws IOException 
     */
   void saveKeys(String fileName, List<IEncryptionKey> keys ) throws FileNotFoundException, IOException;
    
}

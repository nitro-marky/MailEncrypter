/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Saving;

import Exceptions.KeyFileException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import mailencrypter.IEncryptionKey;

/**
 *
 * The interface implemented by the key loader class.
 */
public interface IKeyLoader {
    
    /**
     * 
     * @param fileName The name of the file to load
     * @return List of deserialised encryption keys
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws KeyFileException 
     */
    List<IEncryptionKey> LoadKeys(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException, KeyFileException;
    
}

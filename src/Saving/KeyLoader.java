/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Saving;

import Exceptions.KeyFileException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import mailencrypter.IEncryptionKey;

/**
 *
 * The keyloader class is used to load key and deserialise key files.
 */
public class KeyLoader implements IKeyLoader {
    
    private List<IEncryptionKey> _keyList;
    
    /**
     * Construction for the KeyLoader class, initialises the key list array.
     */
    public KeyLoader()
    {
        _keyList = new ArrayList<>();
    }
    
    
    /**
     * 
     * A basic check is carried out to see if the file is a key file by checking it ends with ".key".
     * Once the stream has been deserialised the list of the keys are returned. If the file is not a key file a null list is returned.
     * @param fileName The file to be loaded
     * @return List of encryption keys from the loaded file
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     * @throws Exceptions.KeyFileException
     */
    @Override
    public List<IEncryptionKey> LoadKeys(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException, KeyFileException
    {
       
        if(fileName.endsWith(".key"))
        {
            FileInputStream _fileInput = new FileInputStream(fileName);
        
            try
            {
                ObjectInputStream _objectFromInput = new ObjectInputStream(_fileInput);
                _keyList = (ArrayList)_objectFromInput.readObject();
                _objectFromInput.close();
                _fileInput.close();
            }
            catch(Exception e)
            {
                 throw new KeyFileException(("LOAD_ERROR"));
            }
            
            return _keyList;
            
        }
        else
        {
            return null;
        }
        
    }
    
}

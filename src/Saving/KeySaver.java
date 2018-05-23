/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Saving;

import java.util.List;
import java.io.*;
import java.util.ArrayList;
import mailencrypter.IEncryptionKey;

/**
 *
 * The key saver serialises a set of keys and saves them to a ".key" file.
 */
public class KeySaver implements IKeySaver {
    
    private List<IEncryptionKey> _keyList;
    
    /**
     * Constructor for the KeySaver class. Initialises the key array list.
     */
    public KeySaver()
    {
        _keyList = new ArrayList<>();    
    }
    
    
    /**
     * 
     * The file is serialised by the file output stream and object output stream and saved to an external
     * ".key" file.
     * @param fileName Name and location to save the file.
     * @param keys List of keys to be serialised.
     * @throws java.io.FileNotFoundException
     */
    @Override
    public void saveKeys(String fileName, List<IEncryptionKey> keys ) throws FileNotFoundException, IOException
    {
        _keyList = keys;
        FileOutputStream _outputStream= new FileOutputStream(fileName + ".key");
        ObjectOutputStream _objectOutStream= new ObjectOutputStream(_outputStream);   
        _objectOutStream.writeObject(_keyList);
        _objectOutStream.close();
        _outputStream.close();
    }
    
    
}

/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Model;

import Exceptions.InvalidDecryptionError;
import Exceptions.KeyFileException;
import Saving.IKeyLoader;
import Saving.IKeySaver;
import Saving.KeySaver;
import Saving.KeyLoader;
import View.IEncryptionObserver;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import mailencrypter.*;

/**
 *
 * Contains the data and functionality relating to the encryption aspect of the program. This includes encryption, decryption and key
 * management.
 */
public class EncryptionModel implements IEncryptionModel, IEncryptionModelObservable {
    
    //The encryption helper objects
    private IKeyManager _keyManager;
    private IEncrypter _encrypter;
    private IDecrypter _decrypter;
    
    //Contains all of the encryption observers
    private List<IEncryptionObserver> _encryptionObservers;
     
     
    /**
     * Initialises the object and field
     */
    public EncryptionModel()
    {
        _keyManager = new EncryptionKeyManager();
        _encrypter = new MessageEncrypter();
        _decrypter = new MessageDecrypter();
        _encryptionObservers = new ArrayList<>();
    }


    /**
     * 
     * Gets the encrypted message from the encrypter which is passed the message and the encryption key which is 
     * retrieved by it's ID from the key manager. Updates the observers with the encrypted message.
     * @param message Message to be encrypted
     * @param id Key that has been selected
     */
    @Override
    public void EncryptMessage(String message, int id)
    {
       if(message != null && !message.isEmpty())
       {
            String _encryptedMessage = _encrypter.encryptMessage(message, _keyManager.getKey(id)); 
            UpdateEncryptionObservers(_encryptedMessage);
       }

    }
    
    
    /**
     * 
     * Works in a similar way to the encrypter. The decrypter is given the message and the key and then returns the message.
     * The observers are updated with the decrypted message.
     * @param message Message to be decrypted.
     * @param id Id of the key needed.
     * @throws Exceptions.InvalidDecryptionError
     */
    @Override
    public void DecryptMessage(String message, int id) throws InvalidDecryptionError
    {
       String _decryptedMessage = _decrypter.decryptMessage(message, _keyManager.getKey(id));
       UpdateEncryptionObservers(_decryptedMessage);
    }

    
    /**
     * Adds an encryption observer to the list. 
     * @param o IEncryptionObserver
     */
    @Override
    public void AddEncryptionObservers(IEncryptionObserver o) {
        _encryptionObservers.add(o);
    }

    
    /**
     * Removes an encryption observer from the list.
     * @param o IEncryptionObserver
     */
    @Override
    public void RemoveEncryptionObservers(IEncryptionObserver o) {
        _encryptionObservers.remove(o);
    }
    

    /**
     * 
     * The key saver is given the file name and current key list for serialisation. Observers are updated with
     * the file name for display.
     * @param fileName Name and location of where to save the file.
     */
    @Override
    public void saveKeys(String fileName) 
    {
        IKeySaver _saver = new KeySaver();
        try 
        {
            _saver.saveKeys(fileName, _keyManager.getKeyList());
            UpdateKeySetName(fileNameSplit(fileName));
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(EncryptionModel.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }

    
    /**
     * 
     * The loader is passed the location of the file which is returned as a list of keys. If the list is valid the keys
     * are given to the key manager and the observers are updated with the current key file name. If the returned list is null
     * a warning is shown and a new set of keys are made.
     * @param fileName - location of file to load. 
     * @throws Exceptions.KeyFileException
     */
    @Override
    public void loadKeys(String fileName) throws KeyFileException
    {
        IKeyLoader _loader = new KeyLoader();
        try 
        {
            List<IEncryptionKey> _keys = _loader.LoadKeys(fileName);
            
            if(_keys != null)
            {
                _keyManager.setKeyList(_keys);
                UpdateKeySetName(fileNameSplit(fileName));
            }
            else
            {
                throw new KeyFileException("LOAD_ERROR");
            }
        } 
        catch (IOException | ClassNotFoundException ex) 
        {
            Logger.getLogger(EncryptionModel.class.getName()).log(Level.SEVERE, null, ex);
        }
                   
    }

    
    /**
     * The key manager is instructed to produce a new set of keys. The observers are given a placeholder name for the 
     * keys which includes the date and time produced.
     */
    @Override
    public void GenerateKeys() {
        _keyManager.GenerateNewKeys();
        UpdateKeySetName("New Keys " + getDate());
    }
    
    
    /**
     * 
     * Updates the observers the a message, could be encrypted or decrypted, it doesn't matter as it only needs to be
     * displayed.
     * @param message Message for observers
     */
    private void UpdateEncryptionObservers(String message)
    {
        for (IEncryptionObserver observer : _encryptionObservers) 
        {
            observer.UpdateMessage(message);
        }
    }
    
    
    /**
     * Returns a string of the current date and time. Used for file naming. 
     */
    private String getDate()
    {
        DateFormat _dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date _date = new Date();
        return _dateFormat.format(_date);
    }
    
    
    /**
     * 
     * Updates observers with the name of the current set of keys.
     * @param name Name of key set
     */
    private  void UpdateKeySetName(String name)
    {
        for(IEncryptionObserver observer : _encryptionObservers)
        {
            observer.UpdateKeySetName(name);
        }
    }
    
    /**
     * 
     *  The file name includes the file path, the split methods is used to separate the name from the location. The name
     * is then returned as a string.
     * @param fileName File name to be split
     */
    private String fileNameSplit(String fileName)
    {
        String _name = new String();
        String[] _fileParts = fileName.split("\\\\");
        if(_fileParts != null)
        {
            _name = _fileParts[_fileParts.length-1];
        }
        return _name;
    }
    
}

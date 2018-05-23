/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package mailencrypter;


import java.util.ArrayList;
import java.util.List;


/**
 *
 * The key manager controls the creation of the keys and also provides access to certain key
 * specific information.
 */
public class EncryptionKeyManager implements IKeyManager {
    
    private List<IEncryptionKey> _keyList;
    private IKeyFactory _keyFactory;
    //The number of keys in each key set.
    private final int _maxKeys = 5;
    
    /**
     * Constructor for the EncryptionKeyManager. Initialises the key list and gets a key factory instance.
     */
    public EncryptionKeyManager()
    {
       _keyList = new ArrayList<>();
       _keyFactory = EncryptionKeyFactory.getInstance();
    }
    
    /**
     * Uses the key factory to make the AES keys using a for loop with the _maxKeys as the upper limit. The returned keys
     * are added to the master list of current keys.
     */
    private void InitialiseKeys()
    {
        _keyList.clear();
            for(int i = 0; i< _maxKeys; i++)
            {
                IEncryptionKey _temp;
                _temp = _keyFactory.MakeEncryptionKey("AES", i);
                _keyList.add(_temp);
            }
    }
    
    
    /**
     * Returns the current list of encryption keys
     * @return 
     */
    @Override
    public List<IEncryptionKey> getKeyList()
    {
        if(!_keyList.isEmpty())
        {
            return _keyList;
        }
        else
        {
            return null;
        }  
    }
    
    /**
     * Returns a key based on it's id.
     * @param id The key to be returned
     * @return Returns encryption key with the specified ID
     */
    @Override
    public IEncryptionKey getKey(int id)
    {
        if(_keyList.isEmpty() || id > _maxKeys)
        {
            return null;
        }
        else
        {
            for(int i = 0; i < _keyList.size(); i++)
            {
                if(i == id)
                {
                    return _keyList.get(id);
                }
            }
        }
        return null;
    }


    /**
     * Public access to the intialise key method.
     */
    @Override
    public void GenerateNewKeys() {
       InitialiseKeys();
    }
    
    
    /**
     * 
     * @param keys New set of keys from a loaded file
     * Used when loading keys to update the master list.
     */
    @Override
    public void setKeyList( List<IEncryptionKey> keys)
    {
        if(keys != null)
        {
            _keyList = keys;
        }
    }

}

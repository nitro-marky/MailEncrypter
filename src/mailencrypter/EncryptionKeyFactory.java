/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package mailencrypter;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * The key factory generates the AES encryption keys
 */
public class EncryptionKeyFactory implements IKeyFactory {
 
    public static EncryptionKeyFactory _instance = null;
    
    private EncryptionKeyFactory()
    {
        
    }
    
    public static EncryptionKeyFactory getInstance()
    {
        if(_instance == null)
        {
            _instance = new EncryptionKeyFactory();
        }
        return _instance;
    }
    
    /**
     * 
     * Uses the javax crypto api to create 128 bit AES keys. 
     * @param type Represents which key needs to be made
     * @param id The key identifier
     * @return New encryption key 
     */
    @Override
    public IEncryptionKey MakeEncryptionKey(String type, int id) {
        
        switch(type.toLowerCase())
        {
            case "aes":
                        KeyGenerator _keyGen = null;  
                        try 
                        {
                            _keyGen = KeyGenerator.getInstance("AES");
                        } 
                        catch (NoSuchAlgorithmException ex) 
                        {
                            Logger.getLogger(EncryptionKeyManager.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //Initialises the 128 bit keys
                        _keyGen.init(128);
                        SecretKey _secretKey = _keyGen.generateKey();
                        IEncryptionKey _tempKey = new AESKey();
                        _tempKey.Initialise(_secretKey, id);
                        return  _tempKey;
        }
        return null;
    }
    
    
      
    
}

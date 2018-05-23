/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package mailencrypter;

import java.io.Serializable;
import javax.crypto.SecretKey;


/**
 *
 * The AESKey stores all of the relevant information needed for AES encryption. Implements
 * the serialisable interface so it can be serialised.
 */
public class AESKey  implements IEncryptionKey, Serializable{

    private SecretKey _secretKey;
    private byte[] _iv = {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
    private int _keyID;
    
    
    /**
     * Sets the secret key and id of the AES key.
     * @param key Secret key value
     * @param id Key identifier
     */
    @Override
    public void Initialise(SecretKey key, int id)
    {
        _secretKey = key;    
        _keyID = id;
    }
    
    /**
     * Returns the AES spec SecretKey 
     * @return The key's SecretKey
     */
    @Override
    public SecretKey getSecretKey()
    {
        return _secretKey;
    }
    
    /**
     * Returns the IV spec
     * @return Key IV byte array
     */
    @Override
    public byte[] getIV()
    {
        return _iv;
    }
    
    /**
     * Returns the key ID
     * @return Key ID integer
     */
    @Override
    public int getKeyID()
    {
        return _keyID;
    }
}

/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package mailencrypter;

import javax.crypto.SecretKey;

/**
 *
 * Interface implemented by the AES keys.
 */
public interface IEncryptionKey {
    
    /**
     *  
     * @return  SecretKey
     */
    SecretKey getSecretKey();
    
    /**
     * 
     * @param key SecretKey object
     * @param id Key identifier number
     */
    void Initialise(SecretKey key, int id);
    
    /**
     * 
     * @return byte array of IV
     */
    byte[] getIV();
    
    /**
     * 
     * @return key id integer 
     */
    int getKeyID();
}

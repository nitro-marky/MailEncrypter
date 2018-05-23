/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package mailencrypter;



import java.util.List;

/**
 *
 * Interface implemented by the key manager.
 */
public interface IKeyManager {
    
    /**
     * 
     * @param id Identifier of the key to be returned
     * @return Encryption key with the same ID
     */
    IEncryptionKey getKey(int id);  
     
    /**
     * 
     * @return list of encryption keys 
     */
    List<IEncryptionKey> getKeyList();
     
    /**
     * 
     * @param keys Replaces the current key list
     */
    void setKeyList( List<IEncryptionKey> keys);
     
    void GenerateNewKeys();
}

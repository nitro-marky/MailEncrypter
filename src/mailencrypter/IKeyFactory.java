/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package mailencrypter;


/**
 *
 * Interface implemented by the key factory.
 */
public interface IKeyFactory {
    
    /**
     * 
     * @param type Reference to type of key
     * @param id The identifier for the key
     * @return New encryption key with that ID
     */
    IEncryptionKey MakeEncryptionKey(String type, int id);
    
    
}

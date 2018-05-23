/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package View;

/**
 *
 * The observer interface implemented by encryption observers.
 */
public interface IEncryptionObserver {
    
    /**
     * 
     * @param message To be displayed in the message area
     */
    void UpdateMessage(String message);
    
    /**
     * 
     * @param name The name of the current key set 
     */
    void UpdateKeySetName(String name);
}

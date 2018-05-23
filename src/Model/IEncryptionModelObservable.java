/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Model;

import View.IEncryptionObserver;

/**
 *
 * Separates the observer management from the main IEncryptionModel interface.
 */
public interface IEncryptionModelObservable {
 
    /**
     * 
     * @param o Encryption Observer
     */
    void AddEncryptionObservers(IEncryptionObserver o);
    
    /**
     * 
     * @param o Encryption Observer
     */
    void RemoveEncryptionObservers(IEncryptionObserver o);
    
}

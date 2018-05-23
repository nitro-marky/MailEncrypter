/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Model;

import View.IEmailObserver;

/**
 *
 * Separates the observer management from the main IEmailModel interface.
 */
public interface IEmailModelObservable {
    
    /**
     * Takes an email observer as a parameter
     * @param o Email observer
     */
    void AddEmailObservers(IEmailObserver o) ;
    
    /**
     * Takes an email observer as a parameter
     * @param o Email observer
     */
    void RemoveEmailObservers(IEmailObserver o);
}

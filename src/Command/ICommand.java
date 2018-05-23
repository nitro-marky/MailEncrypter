/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Command;

/**
 *
 * The ICommand interface enforces the command pattern by ensuring that all command
 * objects contain the same execute method.
 */
public interface ICommand {
    
    /**
     * Execute is the only method required for the ICommand interface. Throws generic exception to
     * accommodate custom exceptions which inherit from Exception.
     * @throws java.lang.Exception
     */
    void Execute() throws Exception;
    
}

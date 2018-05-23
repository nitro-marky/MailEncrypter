/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Exceptions;

/**
 *
 * The InvalidDecryptionError exceptions are created when errors in decryption occur
 */
public class InvalidDecryptionError extends Exception{
    
    private String _message;
    
    /**
     * Constructor for the InvalidDecryptionError
     * @param message String representing the nature of the error.
     */
    public InvalidDecryptionError(String message)
    {
        _message = message;
    }
    
    /**
     * Returns the error message string
     * @return String containing the error description
     */
    @Override
    public String getMessage()
    {
        return _message;
    }
    
}

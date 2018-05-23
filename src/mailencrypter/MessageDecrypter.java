/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package mailencrypter;

import Exceptions.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * Decrypts the message passed to it.
 */
public class MessageDecrypter implements IDecrypter{
    
    
    /**
     * 
     * The cipher is created and set to decrypt mode to decrypt the message. The same padding method used for encryption is 
     * used here to avoid bad padding exceptions. The message string is decoded and converted to a byte array using the base64 
     * decoder as the cipher cannot work directly with Strings. The excessive try-catches are enforced by the javax crypto api.
     * @param message The message to be decrypted
     * @param key The key which encrypted it
     * @return String with the decrypted message
     */
    @Override
    public String decryptMessage(String message, IEncryptionKey key) throws InvalidDecryptionError
    {
        //Creates the cipher and gets the AES instance with PKCS5 padding.
        Cipher _decryptCipher = null;
        try 
        {
            _decryptCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        } 
        catch (NoSuchAlgorithmException | NoSuchPaddingException ex) 
        {
            Logger.getLogger(MessageDecrypter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Sets the cipher to decryption mode and gets the key's secretkey and IV.
        try 
        {
            _decryptCipher.init(Cipher.DECRYPT_MODE, key.getSecretKey(), new IvParameterSpec(key.getIV()));
        } 
        catch (InvalidKeyException | InvalidAlgorithmParameterException ex) 
        {
            Logger.getLogger(MessageDecrypter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        byte[] _decodedMessage = new Base64().decode(message.getBytes());
        byte[] _decryptedMessage = null;
        
        //Tries to decrypt the message, if there is an error an InvalidDecryptionError is thrown.
        try 
        {
            _decryptedMessage = _decryptCipher.doFinal(_decodedMessage);
        } 
        catch (IllegalBlockSizeException ex) 
        {
            throw new InvalidDecryptionError("INCORRECT_MESSAGE");
            
        } catch (BadPaddingException ex) 
        {
             throw new InvalidDecryptionError("INCORRECT_KEY");
        }

        return new String(_decryptedMessage);

    }
   
    
}

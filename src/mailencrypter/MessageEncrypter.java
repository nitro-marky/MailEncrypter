/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package mailencrypter;


import java.io.UnsupportedEncodingException;
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
import sun.misc.BASE64Encoder;

/**
 *
 * Encrypts the message entered by the user
 */
public class MessageEncrypter implements IEncrypter {
    
    /**
     * 
     * Encrypts the supplied method using the supplied encryption key. It uses the same padding
     * as the decrypter. It also uses the base 64 encoder to turn the string into a byte array for 
     * uniformity. Once again there is a comical number of try-catches which are enforced by the crypto api.
     * @param message Message to be encrypted
     * @param key The selected encryption key
     * @return String with the encrypted message
     */
    @Override
    public String encryptMessage(String message, IEncryptionKey key)
    {
        if(key == null)
        {
            return message;    
        }
        else if(message == "" || message == null)
        {          
            return "";
        }
        
        //The instance of the cipher is retrieved with the same encryption type and padding as the decrypter.
        Cipher _encryptionCipher = null;
        try 
        {
            _encryptionCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        } 
        catch (NoSuchAlgorithmException | NoSuchPaddingException ex) 
        {
            Logger.getLogger(MessageEncrypter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //The cipher is initialised and set to encrypt mode and is given the keys secretkey.
        try 
        {
            _encryptionCipher.init(Cipher.ENCRYPT_MODE, key.getSecretKey(), new IvParameterSpec(key.getIV()));
        } 
        catch (InvalidKeyException | InvalidAlgorithmParameterException ex) 
        {
            Logger.getLogger(MessageEncrypter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        byte[] _messageBytes = null;
        
        try 
        {
            _messageBytes = message.getBytes("UTF8");
        } 
        catch (UnsupportedEncodingException ex) 
        {
            Logger.getLogger(MessageEncrypter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        byte[] _encrypted = null;
        
        //The message is encrypted using the doFinal method.
        try 
        {
            _encrypted = _encryptionCipher.doFinal(_messageBytes);
        } 
        catch (IllegalBlockSizeException | BadPaddingException ex) 
        {
            Logger.getLogger(MessageEncrypter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String _message = new BASE64Encoder().encode(_encrypted);
        return _message;
        
    }
    
 
           

    
    
}

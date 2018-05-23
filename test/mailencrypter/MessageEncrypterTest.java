/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package mailencrypter;

import javax.net.ssl.KeyManager;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 13012555
 */
public class MessageEncrypterTest {
    
            
        MessageEncrypter _encrypter = new MessageEncrypter();
        EncryptionKeyManager _keyManager = new EncryptionKeyManager();
    
    
    public MessageEncrypterTest() 
    {        
         _keyManager.GenerateNewKeys();
    }

    /**
     * Tests if the output message is the same as the original input if the key is null
     */
    @Test
    public void testEncryptMessage() 
    {
        
        String _message = "Hello World";
        String _testMessage = _encrypter.encryptMessage(_message, null);
        assertEquals(_message, _testMessage);
    }
    
    /**
     * Tests if the output from a blank message and null key is a blank message.
     */
    @Test
    public void testEncryptMessage2() 
    {
        String _expectedTestOutcome = "";
        String _testMessage = _encrypter.encryptMessage("", null);
        assertEquals(_expectedTestOutcome, _testMessage);
    }
    
    /**
     * Tests if the output is the same as the input with a blank string.
     */
    @Test
    public void testEncryptMessage3() 
    {
        String _expectedTestOutcome = "";
        String _testMessage = _encrypter.encryptMessage("", _keyManager.getKey(0));
        assertEquals(_testMessage, _expectedTestOutcome);
    }
    
    /**
     * Test if the output message is the same as the input when a key index greater than the range is entered.
     */
    @Test
    public void testEncryptMessage4() 
    {
        String _testInput = "Test 4 message";
        String _testMessage = _encrypter.encryptMessage(_testInput, _keyManager.getKey(99));
        System.out.println("Test 4 output: " + _testMessage);
        assertEquals(_testInput, _testMessage);
    }    
    
    /**
     * Tests if the output is not null with a valid key and no message.
     */
    @Test
    public void testEncryptMessage5() 
    {     
        String _testMessage = _encrypter.encryptMessage(null, _keyManager.getKey(0));
        System.out.println("Test 5 output: " + _testMessage);
        assertNotNull("", _testMessage);   
    }   
    
    /**
     * Tests to confirm that the output of the same string encrypted with the same key is equal.
     */
    @Test
    public void testEncryptMessage6() 
    {     
        String _testInput = "Hello World";
        String _testMessage = _encrypter.encryptMessage(_testInput, _keyManager.getKey(4));
        String _testMessageVersion2 = _encrypter.encryptMessage(_testInput, _keyManager.getKey(4));
        assertEquals(_testMessage, _testMessageVersion2);  
    }     
    
    
}

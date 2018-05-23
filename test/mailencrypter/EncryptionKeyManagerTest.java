/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package mailencrypter;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 13012555
 */
public class EncryptionKeyManagerTest {
    
    public EncryptionKeyManagerTest() {
    }

    /**
     * Tests what is returned before any keys are created.
     */
    @Test
    public void testGetKeyList() 
    {
        
        EncryptionKeyManager _manager = new EncryptionKeyManager();
        List<IEncryptionKey> _keyList = _manager.getKeyList();
        assertNull(_keyList);   
    }
    
    /**
     * Tests what is returned before after keys are created.
     */
    @Test
    public void testGetKeyList2() 
    {
        
        EncryptionKeyManager _manager = new EncryptionKeyManager();
        _manager.GenerateNewKeys();
        List<IEncryptionKey> _keyList = _manager.getKeyList();
        assertNotNull(_keyList);   
    }

    /**
     * Test if a returned key is null before keys are created.
     */
    @Test
    public void testGetKey() 
    {
        EncryptionKeyManager _manager = new EncryptionKeyManager();
        IEncryptionKey _testKey = _manager.getKey(1);
        assertNull(_testKey);
    }
    
    /**
     * Test if a returned key is not null after keys are created.
     */
    @Test
    public void testGetKey2() 
    {
        EncryptionKeyManager _manager = new EncryptionKeyManager();
        _manager.GenerateNewKeys();
        IEncryptionKey _testKey = _manager.getKey(1);
        assertNotNull(_testKey);
    }
    
    /**
     * Test if a returned key is null if extreme key indexes are entered.
     */
    @Test
    public void testGetKey3() 
    {
        EncryptionKeyManager _manager = new EncryptionKeyManager();
        _manager.GenerateNewKeys();
        IEncryptionKey _testKey = _manager.getKey(8000000);
        assertNull(_testKey);
        
        IEncryptionKey _testKey2 = _manager.getKey(-5000000);
        assertNull(_testKey2);
        
        IEncryptionKey _testKey3 = _manager.getKey(90);
        assertNull(_testKey3);        
    }
    
    

    /**
     * Tests what the outcome is when a null list is passed through the set key list.
     */
    @Test
    public void testSetKeyList() 
    {
        EncryptionKeyManager _manager = new EncryptionKeyManager();
        _manager.setKeyList(null);
        List<IEncryptionKey> _testKeys = _manager.getKeyList();
        assertNull(_testKeys);
    }
    

    
}

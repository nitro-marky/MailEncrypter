/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Command;

import Model.EncryptionModel;
import org.junit.Test;

/**
 *
 * @author ADD
 */
public class KeyGenerateCommandTest {
    
    public KeyGenerateCommandTest() {
    }

    /**
     * Test of Execute method, of class KeyGenerateCommand.
     */
    @Test
    public void testExecute() {
        
        KeyGenerateCommand _command = new KeyGenerateCommand(null);
        
            _command.Execute();
    }
    
    /**
     * Tests the execute method with a model.
     */    
    @Test
    public void testExecute2()
    {
        KeyGenerateCommand _command = new KeyGenerateCommand(new EncryptionModel());
        _command.Execute();
    }
    
}

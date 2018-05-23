/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Command;

import Exceptions.MailException;
import Model.EmailModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;


/**
 *
 * @author 13012555
 */
public class CheckEmailCommandTest {
    
    public CheckEmailCommandTest() {
    }

    /**
     * Tests the execute method with a null model reference.
     * 
     */
    @Test
    public void testExecute() 
    {
        CheckEmailCommand _command = new CheckEmailCommand(null);
        try {
            _command.Execute();
        } catch (MailException ex) {
            Logger.getLogger(CheckEmailCommandTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Tests the execute method with a model.
     */
    @Test
    public void testExecute2()
    {
        CheckEmailCommand _command = new CheckEmailCommand(new EmailModel());
        try {
            _command.Execute();
        } catch (MailException ex) {
            Logger.getLogger(CheckEmailCommandTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}

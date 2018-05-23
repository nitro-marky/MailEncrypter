/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Command;

import Controller.Controller;
import Controller.IEmailStrategy;
import View.EmailView;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 13012555
 */
public class EmailAccountCommandTest {
    
    public EmailAccountCommandTest() {
    }

    /**
     * Tests the execute method with a null email view.
     */
    @Test
    public void testExecute() 
    {
        EmailAccountCommand _command = new EmailAccountCommand(null);
        _command.Execute();
    }
    
    /**
     * Tests the execute method with an email view but no defined email strategy.
     */
    @Test
    public void testExecute2() 
    {
        EmailAccountCommand _command = new EmailAccountCommand(new EmailView(null));
        _command.Execute();
    }
    
    /**
     * Tests the execute method with an email view and a defined email strategy.
     */
    @Test
    public void testExecute3() 
    {
        EmailAccountCommand _command = new EmailAccountCommand(new EmailView((IEmailStrategy)Controller.getInstance()));
        _command.Execute();
    }
    
}

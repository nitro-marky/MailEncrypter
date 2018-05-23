/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Command;

import Exceptions.MailException;

/**
 *
 * The IEmailCommand is used by commands which need the ability to throw a specific custom MailException.
 */
public interface IEmailCommand extends ICommand {
    
    @Override
    void Execute() throws MailException;
    
}

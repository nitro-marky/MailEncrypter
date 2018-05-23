/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Command;

import View.EmailView;

/**
 * The EmailAccountCommand is used when the user clicks on 'Email Account' in the MainView, this
 * command sets the view to visisble so the email address and password can be 
 * entered.
 * 
 */
public class EmailAccountCommand implements ICommand{

    
    private EmailView _view;
    
    /**
     * Constructor for the EmailAccountCommand.
     * @param view EmailView required for the Execute.
     */
    public EmailAccountCommand(EmailView view)
    {
        _view = view;
    }
    
    /**
     *  Sets the EmailView visibility to true;
     * 
     */
    @Override
    public void Execute() 
    {
        if(_view != null)
        {
            _view.setVisible(true);
        }
    }
    
}

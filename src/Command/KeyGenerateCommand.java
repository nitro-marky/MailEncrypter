/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package Command;


import Model.IEncryptionModel;

/**
 *
 * The KeyGenerateCommand calls on the encryption model to generate new encryption keys. This
 * occurs when the user clicks the 'Generate New Keys' button in the MainView.
 */
public class KeyGenerateCommand implements ICommand {

    private IEncryptionModel _model;
    
    /**
     * Constructor for the KeyGenerateCommand
     * @param model IEncryptionModel needed for Execute
     */
    public KeyGenerateCommand(IEncryptionModel model)
    {
        _model = model;
    }
   
    /**
     * The execute method calls on the model to generate keys. 
     */
    @Override
    public void Execute() 
    {    
        if(_model != null)
        {
            _model.GenerateKeys();   
        }
    }
    
}

/*
 * Mail Encrypter Created by 13012555  
 *   
 */
package mailencrypter;

import Exceptions.InvalidDecryptionError;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 13012555
 */
public class MessageDecrypterTest 
{
    
    EncryptionKeyManager _keyManager; 
    MessageEncrypter _encrypter = new MessageEncrypter();
    MessageDecrypter _decrypter = new MessageDecrypter();
    
    /**
     * Instantiates the fields that will be used by the methods.
     */    
    public MessageDecrypterTest() 
    {    
        _keyManager = new EncryptionKeyManager();
        _keyManager.GenerateNewKeys();
    }

    /**
     * Tests if the output is the same as the input before and after the encryption/decryption process.
     */
    @Test
    public void testDecryptMessage1() throws InvalidDecryptionError 
    {
        String _testMessage = "Encryption Test";
        String _encrypted = _encrypter.encryptMessage(_testMessage, _keyManager.getKey(0));
        String _decrypted = _decrypter.decryptMessage(_encrypted, _keyManager.getKey(0));
        assertEquals(_testMessage, _decrypted);       
    }
    
    /**
     * Tests that the custom InvalidDecryptionError is thrown when the incorrect encryption
     * key is used for decryption.
     */    
    @Test(expected=InvalidDecryptionError.class)
    public void testDecryptMessage2() throws InvalidDecryptionError {
        
        String _testMessage = "Encryption Test";
        String _encrypted = _encrypter.encryptMessage(_testMessage, _keyManager.getKey(0));
        String _decrypted = _decrypter.decryptMessage(_encrypted, _keyManager.getKey(1));
    
    }
    
    /**
     * Tests if the output is the same as the input when special characters are used. This provides an issue as when using the
     * program special characters work fine, however, the test displays an error and shows the output has an 
     * erroneous 'Â' symbol.
     */
    @Test
    public void testDecryptMessage3() throws InvalidDecryptionError
    {
        //Test to see if special characters are encrypted and decrypted correctly.
        String _testInput = "£$%^&*";
        String _testMessage = _encrypter.encryptMessage(_testInput, _keyManager.getKey(1));
        String _testMessageDecrypt = _decrypter.decryptMessage(_testMessage, _keyManager.getKey(1));
        System.out.println("Input: " +_testInput + ", Output: " + _testMessageDecrypt);
        assertSame(_testInput, _testMessageDecrypt);   
    }
    
    /**
     * Tests encryption and decryption of long string which contains 529 words and 3595 bytes.
     */
    @Test
    public void testDecryptMessage4() throws InvalidDecryptionError
    {
           
        String _testInput = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi nec nisl ac magna posuere tincidunt in fringilla orci. In imperdiet, ligula sed venenatis porta, ipsum metus sollicitudin dolor, at tincidunt velit eros et lectus. Pellentesque ex augue, pharetra vel ante vitae, interdum molestie metus. Nulla nec magna a mi hendrerit ultricies. Nam ultricies ipsum eget urna varius, ac semper ipsum pulvinar. Maecenas a volutpat leo. Vivamus ex erat, eleifend eget orci ut, vulputate vehicula sapien. Mauris gravida feugiat ornare. Fusce in enim aliquet, maximus lectus nec, dapibus turpis. Praesent dapibus lorem semper, vestibulum diam vel, dictum risus. Vivamus porta ipsum massa, eget lacinia odio scelerisque sed. Vestibulum nibh lorem, efficitur a mauris in, hendrerit dictum felis. Donec dapibus nunc vulputate laoreet placerat.\n" +
        "\n" +
        "Etiam tempor elit ut mauris vehicula tincidunt nec a nisi. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. In metus leo, convallis a commodo ornare, faucibus quis enim. Cras ultrices nibh in ante faucibus, nec pulvinar neque commodo. Nunc ultrices quam nulla, sit amet auctor dolor sodales nec. Nullam feugiat viverra mattis. Quisque porttitor, quam quis ornare tempus, augue turpis hendrerit lacus, ac congue purus massa eget eros. Donec at arcu sit amet orci consequat faucibus. Curabitur commodo eros odio, quis placerat turpis condimentum ac. Sed aliquet diam purus, nec pellentesque risus pretium eget. Nullam dapibus sollicitudin est in bibendum. Nulla nec nulla augue. In nec efficitur nisi, a suscipit turpis.\n" +
        "\n" +
        "Vestibulum at sem vel dui blandit vulputate et et elit. Etiam pellentesque maximus vehicula. Aenean vel urna sagittis felis scelerisque mattis. Mauris feugiat a nulla vehicula ullamcorper. Vivamus a feugiat quam. Donec tellus quam, volutpat in erat quis, vulputate tincidunt urna. Morbi in elementum sapien. Suspendisse potenti. Quisque elementum velit non est aliquam condimentum. Cras erat urna, volutpat aliquam tortor id, ultrices venenatis sapien. Nunc justo turpis, imperdiet ac placerat tempor, mattis blandit nulla. Nam sed lorem vitae ipsum efficitur ultricies sed sit amet risus. Suspendisse consectetur velit ac quam mollis laoreet. Vivamus scelerisque purus sit amet arcu volutpat, in tincidunt tortor tempus. Sed sed ante vitae ipsum mattis tempor consequat nec sapien.\n" +
        "\n" +
        "In pulvinar, urna nec euismod faucibus, lacus dui eleifend quam, et facilisis libero nulla nec velit. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras cursus, neque vitae scelerisque suscipit, tellus justo ullamcorper mi, ut mollis elit sapien ut sapien. Suspendisse eu porta leo, eu dignissim eros. Praesent interdum nisi nulla, vitae scelerisque ipsum dignissim sed. Nullam congue nulla in ipsum rutrum ullamcorper. Aenean id elementum eros. Cras mattis, enim ut finibus condimentum, neque massa eleifend purus, ut tempor dui ex vitae purus. Sed in felis non dolor efficitur volutpat laoreet ac tortor. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Phasellus consectetur ullamcorper volutpat.\n" +
        "\n" +
        "Aliquam justo elit, pharetra et sollicitudin a, pulvinar vitae felis. Pellentesque tincidunt augue at velit fermentum, in fringilla diam tempor. Integer nisi enim, placerat sit amet bibendum eu, convallis at lacus. Phasellus facilisis placerat tempor. Sed consectetur in odio et feugiat. Phasellus semper nulla urna, ac fringilla arcu eleifend nec. Morbi lorem enim, sodales vel commodo nec, posuere viverra massa. Nulla tincidunt velit ut venenatis tincidunt. ";
    
        String _encrypted = _encrypter.encryptMessage(_testInput, _keyManager.getKey(0));
        String _decrypted = _decrypter.decryptMessage(_encrypted, _keyManager.getKey(0));
        
        assertEquals(_testInput, _decrypted);
      
    }
    
    /**
     * Tests encryption and decryption of very long string which contains 1443 words and 9879 bytes.
     */    
    @Test
    public void testDecryptMessage5() throws InvalidDecryptionError
    {
           
        String _testInput = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris nec risus aliquam, ornare libero et, pulvinar tellus. Vivamus pulvinar condimentum lectus, sit amet suscipit nulla pellentesque feugiat. Sed iaculis, lectus et aliquam malesuada, lorem lectus molestie neque, eget ultricies purus neque quis velit. Pellentesque vitae sapien vel ante sollicitudin sagittis. Aliquam in neque eros. Maecenas libero arcu, pellentesque aliquam laoreet pellentesque, scelerisque vel felis. Vestibulum fringilla ut orci sed euismod. Sed ultrices posuere felis quis euismod. Mauris vel quam orci. Nullam pharetra, risus et vehicula congue, metus elit gravida massa, ac tincidunt turpis turpis vel nulla. Nullam lobortis fermentum hendrerit. Duis in elementum mauris. Curabitur rhoncus augue eu purus euismod, vel dictum magna porta. Integer vestibulum nunc a lacinia maximus. Donec et pellentesque odio. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.\n" +
        "\n" +
        "Donec sollicitudin turpis in hendrerit pulvinar. Vivamus interdum lectus non vulputate sagittis. Cras aliquet neque eu iaculis condimentum. Proin massa nunc, malesuada vel efficitur et, sollicitudin euismod orci. Ut sollicitudin urna ante, vitae hendrerit velit tempus maximus. Donec posuere ornare viverra. Nam congue congue pretium. Morbi a iaculis leo. Proin mollis et nunc a egestas. Etiam gravida interdum justo eu ullamcorper. Duis ullamcorper massa in lectus interdum laoreet. Nunc et leo magna. Nam vestibulum lobortis accumsan. Cras ornare ligula in ligula efficitur, bibendum sagittis arcu ullamcorper.\n" +
        "\n" +
        "Vestibulum fringilla rutrum semper. Nam eu dapibus erat, a dictum nibh. Morbi lectus ipsum, ultricies id lacus et, placerat scelerisque felis. Cras facilisis non neque in venenatis. Donec varius at neque eu venenatis. In quis lectus pellentesque, condimentum dui facilisis, sollicitudin mauris. Phasellus a pellentesque tellus. Phasellus dapibus, mauris at fermentum congue, nibh enim scelerisque justo, vel tempor nisi mauris et turpis. Aliquam tincidunt scelerisque diam, sit amet tempor enim laoreet ut. Sed sollicitudin risus lorem, sit amet semper erat feugiat at. Praesent aliquet ut sapien ut sodales. Mauris maximus facilisis nulla a tempus. Integer interdum, turpis at porta venenatis, nisl sapien ultrices risus, eget egestas nisl libero vitae neque. Quisque eget tellus nec sapien pretium suscipit. Morbi ac justo eu dolor tincidunt luctus. Vestibulum tincidunt placerat nibh, eu ornare neque maximus ac.\n" +
        "\n" +
        "Sed dictum arcu sed lorem aliquam, et condimentum lorem maximus. Vivamus hendrerit ex est, vel molestie odio egestas nec. Etiam eget commodo neque, ultrices porta erat. In consequat pellentesque metus, quis interdum elit molestie ornare. Sed lobortis, orci vitae condimentum pretium, felis justo ornare ante, in iaculis erat massa a sapien. Nulla et ligula est. Integer blandit mi ut massa tempus, ac consequat libero venenatis. Integer sagittis mauris ullamcorper tortor dignissim consequat quis in leo. Donec dui augue, convallis nec cursus feugiat, porta in ex. Etiam ullamcorper, est sit amet auctor ullamcorper, felis dolor cursus nisl, nec placerat massa nisi sed magna. Suspendisse laoreet gravida ante non auctor. Donec nec tellus vitae ipsum interdum varius. Vivamus molestie lorem a ultricies lobortis. Fusce sit amet ultricies mi. Sed sodales, lacus a pharetra congue, turpis ante mollis nisi, elementum lobortis nulla risus quis enim. Ut eu est vitae turpis auctor eleifend.\n" +
        "\n" +
        "Nullam metus est, consectetur at turpis a, porta sagittis orci. Nullam maximus iaculis metus, et bibendum mi sodales nec. Maecenas pellentesque euismod arcu, quis sagittis lorem accumsan eget. Curabitur ultrices quis ante quis posuere. Maecenas aliquam sapien et scelerisque venenatis. Donec vitae orci felis. Nunc feugiat felis vitae commodo commodo. Ut quis sem quis magna facilisis efficitur quis et tortor. Maecenas fringilla enim ac lorem ultrices ullamcorper.\n" +
        "\n" +
        "Donec facilisis vulputate tempus. Nam finibus euismod dolor, et viverra massa laoreet a. Nullam vitae justo consequat, pretium sapien id, ullamcorper eros. Praesent scelerisque placerat ornare. Morbi mauris neque, pulvinar id dui sed, ultrices finibus felis. Sed sed enim nec mauris laoreet accumsan. Mauris pretium erat vitae mi viverra vulputate.\n" +
        "\n" +
        "Donec rutrum elementum ipsum, non finibus risus semper nec. Phasellus pretium id tellus eu dictum. Nulla in justo vel risus iaculis sodales. Sed mollis, nulla sed pharetra tincidunt, nisl nunc aliquam augue, vitae porta nunc lectus eget quam. Sed pellentesque a tellus sit amet lacinia. Donec sed tincidunt massa. Integer non lacus et dolor accumsan porta. Fusce pulvinar non arcu quis elementum. Nulla consequat diam scelerisque elementum viverra. Proin feugiat laoreet eros facilisis suscipit. Nam consequat consectetur pharetra. Phasellus sodales ligula diam, vitae vehicula metus tincidunt at. Pellentesque efficitur ultrices ipsum, et eleifend metus porta non.\n" +
        "\n" +
        "Maecenas felis eros, rutrum vitae lacus sit amet, ultricies vehicula libero. Sed fermentum eu nulla in ultrices. Donec mattis ipsum venenatis nunc rutrum iaculis. Suspendisse sed dui purus. Suspendisse blandit feugiat egestas. Fusce risus nisi, laoreet a nunc in, semper fermentum orci. Fusce vehicula, elit eget iaculis bibendum, mi ex sagittis enim, quis egestas odio odio at risus. Vestibulum vitae semper arcu. Pellentesque et malesuada turpis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Quisque nec ex id arcu sagittis varius a ullamcorper quam.\n" +
        "\n" +
        "Duis pretium ultrices dapibus. Etiam suscipit egestas libero a consectetur. Nulla dui lectus, ornare volutpat ligula congue, porttitor rhoncus odio. In eget erat id turpis luctus dapibus eget lobortis nisl. Aenean sed consequat mi. Fusce molestie a turpis aliquet eleifend. Morbi vel ultrices ante. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed eu metus enim. Quisque sit amet tincidunt augue, ut accumsan lorem. Nulla tempus, ex in egestas malesuada, lorem erat vestibulum mi, at tempus sem ex id justo. Proin eu augue egestas eros ullamcorper gravida.\n" +
        "\n" +
        "Sed sollicitudin fermentum condimentum. Pellentesque vestibulum est eget consequat sollicitudin. Maecenas lobortis mauris eu neque facilisis, ac convallis libero laoreet. Sed ac dignissim urna, nec tristique neque. Sed interdum pharetra sapien, posuere feugiat justo lobortis eu. Etiam a tempor nisl, at semper massa. Phasellus ut accumsan mi, nec aliquet nisl. Aenean elementum quis lacus id suscipit. Quisque sodales arcu maximus dui mattis dictum. Nulla eget est eu lectus aliquet venenatis.\n" +
        "\n" +
        "Vestibulum sed rhoncus sem, et feugiat erat. Donec quis semper nibh. Morbi ornare dui eget porta lacinia. Duis felis risus, auctor vitae eleifend in, luctus et felis. Sed porttitor facilisis molestie. Pellentesque condimentum consectetur aliquam. Etiam maximus semper pharetra. Nulla luctus, enim eu porta faucibus, elit libero tincidunt elit, non pretium est risus mollis tortor. Mauris lacinia purus ac imperdiet lacinia. Vivamus luctus ac sem id vulputate. Vivamus sodales diam eget odio dignissim, a aliquet felis tincidunt. Phasellus a ipsum id libero elementum interdum. Mauris leo nibh, elementum eget leo at, ultrices scelerisque turpis. Ut pellentesque neque convallis ipsum varius, iaculis vehicula lectus sodales. Donec eget eros bibendum, suscipit mi ut, imperdiet enim. Curabitur sit amet nisi lorem.\n" +
        "\n" +
        "Aenean semper venenatis magna vel rutrum. Fusce laoreet massa at nunc scelerisque, vitae viverra nibh consequat. In sed dapibus leo. Fusce quis urna at eros porta placerat. Nunc faucibus, elit quis vestibulum eleifend, purus quam lacinia lorem, ac iaculis nunc ex vitae massa. Duis non tellus id velit commodo dapibus. Aliquam nec nunc vel mi condimentum congue. Nullam tincidunt tempor egestas. Integer aliquet leo at finibus sollicitudin. Vestibulum vitae dictum erat. Pellentesque at placerat nulla. Donec sagittis commodo lobortis. Nam interdum et felis sit amet scelerisque. Nam quis tortor ipsum. In vehicula purus ut tellus finibus, non ultrices tellus fringilla. Sed porttitor ac est ut placerat.\n" +
        "\n" +
        "Etiam mattis, libero sed interdum congue, libero justo egestas ligula, ultrices condimentum nunc turpis eu leo. Donec vitae eros egestas magna ornare eleifend. Phasellus at hendrerit neque. Maecenas dictum dui et cursus mollis. Aenean fermentum dui lacus, et efficitur mi mattis vel. Mauris eu magna et quam cursus sodales. Donec aliquet scelerisque lorem, sed vulputate quam faucibus ut. Duis nec quam laoreet, sodales velit et, elementum orci. Nullam ac odio vitae quam sodales commodo. Nunc ac hendrerit nunc. Praesent porttitor mi nec odio posuere elementum. Maecenas in magna mollis, venenatis orci id, volutpat eros. Mauris posuere quam fermentum mattis vulputate. Proin sed est porttitor, rutrum lacus fringilla, elementum sem.\n" +
        "\n" +
        "Aliquam leo metus, mollis ullamcorper pharetra in, lobortis vitae nulla. In luctus lacus ac imperdiet vestibulum. Sed justo nibh, ullamcorper nec erat vitae, dictum condimentum lacus. Nunc eget lectus erat. Cras luctus, quam at sagittis vestibulum, urna nisi placerat felis, quis rutrum nulla diam nec nisi. Nullam ornare erat ac accumsan venenatis. Etiam malesuada leo at sapien varius volutpat. Aliquam erat volutpat. Praesent aliquam lorem ornare tristique pretium. Suspendisse potenti.\n" +
        "\n" +
        "Donec ac feugiat erat, sit amet viverra massa. Nullam porttitor ac diam at pellentesque. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Curabitur auctor turpis vel ultricies congue. Nunc ac augue eu ipsum porta dictum. Pellentesque laoreet magna et convallis convallis. Nunc purus metus, convallis quis mattis ac, consequat sit amet sem. Cras ac diam nec orci lobortis hendrerit in non quam. Duis hendrerit justo ac iaculis fermentum.";
    
        String _encrypted = _encrypter.encryptMessage(_testInput, _keyManager.getKey(0));
        String _decrypted = _decrypter.decryptMessage(_encrypted, _keyManager.getKey(0));
        
        assertEquals(_testInput, _decrypted);
      
    }    
   
    
}

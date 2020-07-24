package configuration;

import org.junit.Test;
import twitter4j.Twitter;

public class ApplicationConfigurationTest {
    @Test(expected = Exception.class)
    public void loadKeysTest(){
        ApplicationConfiguration c = new ApplicationConfiguration("filethatdoesntexist");
        Twitter t = c.createTwitter();
    }
}
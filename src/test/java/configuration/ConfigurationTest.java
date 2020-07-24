package configuration;

import org.junit.Test;
import twitter4j.Twitter;

public class ConfigurationTest {
    @Test(expected = Exception.class)
    public void loadKeysTest(){
        Configuration c = new Configuration("filethatdoesntexist");
        Twitter t = c.createTwitter();
    }
}
package configuration;

import org.junit.Test;
import twitter4j.Twitter;

import static org.junit.Assert.*;

public class ConfigurationTest {
    // TODO: test loadkeys error
    @Test(expected = Exception.class)
    public void loadKeysTest(){
        Configuration c = new Configuration("filethatdoesntexist");
        Twitter t = c.createTwitter();
    }
}
package configuration;

import org.junit.Test;
import twitter4j.Twitter;

import java.io.IOException;

public class ApplicationConfigurationTest {
    @Test(expected = Exception.class)
    public void loadKeysTest() throws IOException {
        ApplicationConfiguration c = new ApplicationConfiguration("filethatdoesntexist");
        Twitter t = c.createTwitter();
    }
}
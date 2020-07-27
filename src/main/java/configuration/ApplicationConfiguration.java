package configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.dropwizard.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.File;
import java.io.IOException;

public class ApplicationConfiguration extends Configuration {
    private static Logger logger = LoggerFactory.getLogger(ApplicationConfiguration.class);

    @JsonProperty
    private String oauthconsumerkey;
    @JsonProperty
    private String oauthconsumersecret;
    @JsonProperty
    private String oauthaccesstoken;
    @JsonProperty
    private String oauthaccesstokensecret;

    private String path;

    /** Constructor, sets path to default value
     * */
    public ApplicationConfiguration() {
        this.path = "./config.yml";
        logger.debug("Configuration created with empty constructor");
    }

    /** Constructor
     * @param path: value for Configuration's path property
     * */
    public ApplicationConfiguration(String path){
        this.path = path;
        logger.debug("Configuration created with path = {}", path);
    }

    /** Loads twitter credential keys from file specified by path property
     * @return Configuration with private key properties set to loaded key values
     * */
    private ApplicationConfiguration loadKeys() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            logger.debug("loadKeys() attempting to read values from: {}", this.path);
            return mapper.readValue(new File(this.path), ApplicationConfiguration.class);
        } catch (IOException e) {
            logger.error("Exception was thrown: {}. Path corresponding to this event: {}.", e.getMessage(), this.path, e);
            throw e;
        }
    }


    /** Creates Twitter object using a Configuration's key properties.
     * @return Twitter with loaded credentials
     * */
    public Twitter createTwitter() throws IOException {
        ApplicationConfiguration c = this.loadKeys();
        logger.debug("createTwitter loaded keys successfully from {}", this.path);
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(c.oauthconsumerkey)
                .setOAuthConsumerSecret(c.oauthconsumersecret)
                .setOAuthAccessToken(c.oauthaccesstoken)
                .setOAuthAccessTokenSecret(c.oauthaccesstokensecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }
}
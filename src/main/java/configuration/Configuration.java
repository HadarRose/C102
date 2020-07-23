package configuration;// This class exists because dropwizard application's .run() function is dependant on having a config class
// but in reality, we don't have anything to configure atm

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import resources.TwitterRequestResource;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.File;

public class Configuration extends io.dropwizard.Configuration {
    @JsonProperty
    private String oauthconsumerkey;
    @JsonProperty
    private String oauthconsumersecret;
    @JsonProperty
    private String oauthaccesstoken;
    @JsonProperty
    private String oauthaccesstokensecret;

    private static Logger logger = LoggerFactory.getLogger(Configuration.class);
    private String path;

    /** Constructor, sets path to default value
     * */
    public Configuration() {
        this.path = "./config.yml";
        logger.debug("Configuration created with empty constructor");
    }

    /** Constructor
     * @param path: value for Configuration's path property
     * */
    public Configuration(String path){
        this.path = path;
        logger.debug("Configuration created with path = {}", path);
    }

    /** Loads twitter credential keys from file specified by path property
     * @return Configuration with private key properties set to loaded key values
     * */
    private Configuration loadKeys() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            logger.debug("loadKeys() attempting to read values from: {}", this.path);
            return mapper.readValue(new File(this.path), Configuration.class);
        } catch (Exception e) {
            logger.debug("Path when exception thrown: {}", this.path);
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /** Creates Twitter object using a Configuration's key properties.
     * @return Twitter with loaded credentials
     * */
    public Twitter createTwitter() {
        Configuration c = this.loadKeys();
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
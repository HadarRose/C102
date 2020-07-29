package services.twitter4j;

import configuration.ApplicationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.ConfigurationService;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;

public class TwitterCreationService {
    private static Logger logger = LoggerFactory.getLogger(TwitterCreationService.class);

    private ApplicationConfiguration applicationConfiguration;
    private ConfigurationService configurationService;

    /**
     * Constructor. Creates a new ConfigurationService and uses its getKeys() to generate ApplicationConfiguration
     */
    public TwitterCreationService() throws IOException {
        logger.info("TwitterCreationService created");
        configurationService = new ConfigurationService();
        applicationConfiguration = configurationService.getKeys(); // todo this aint contained to services.twitter4j
    }

    /**
     * @return ConfigurationBuilder with the credentials received from applicationConfiguration
     */
    private ConfigurationBuilder createConfigBuilder() {
        logger.info("TwitterCreationService called createConfigBuilder");
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(applicationConfiguration.getOauthconsumerkey())
                .setOAuthConsumerSecret(applicationConfiguration.getOauthconsumersecret())
                .setOAuthAccessToken(applicationConfiguration.getOauthaccesstoken())
                .setOAuthAccessTokenSecret(applicationConfiguration.getOauthaccesstokensecret());
        return cb;
    }

    /**
     * Creates Twitter object using a Configuration's key properties.
     *
     * @return Twitter with loaded credentials
     */
    public Twitter createTwitter() {
        logger.info("TwitterCreationService called createTwitter");
        TwitterFactory tf = new TwitterFactory(this.createConfigBuilder().build());
        return tf.getInstance();
    }
}

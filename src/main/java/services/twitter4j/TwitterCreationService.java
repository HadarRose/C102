package services.twitter4j;

import model.TwitterKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterCreationService {
    private static Logger logger = LoggerFactory.getLogger(TwitterCreationService.class);

    private TwitterKeys twitterKeys;

    /**
     * Constructor
     *
     * @param twitterKeys TwitterKeys containing keys for new twitter object
     */
    public TwitterCreationService(TwitterKeys twitterKeys) {
        this.twitterKeys = twitterKeys;
        logger.info("TwitterCreationService created");
    }

    /**
     * @return ConfigurationBuilder with the credentials received from applicationConfiguration
     */
    private ConfigurationBuilder createConfigBuilder() {
        logger.info("TwitterCreationService called createConfigBuilder");
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(twitterKeys.getOauthconsumerkey())
                .setOAuthConsumerSecret(twitterKeys.getOauthconsumersecret())
                .setOAuthAccessToken(twitterKeys.getOauthaccesstoken())
                .setOAuthAccessTokenSecret(twitterKeys.getOauthaccesstokensecret());
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

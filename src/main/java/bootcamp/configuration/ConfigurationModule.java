package bootcamp.configuration;

import dagger.Module;
import dagger.Provides;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Module
public class ConfigurationModule {
    private static Logger logger = LoggerFactory.getLogger(ConfigurationModule.class);
    private Twitter twitter;

    /**
     * Constructor
     *
     * @param applicationConfiguration the ApplicationConfiguration used to retrieve the twitter keys
     */
    public ConfigurationModule(ApplicationConfiguration applicationConfiguration) {
        logger.debug("ConfigurationModule construction started");
        TwitterKeys twitterKeys = applicationConfiguration.getTwitterKeys();
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(twitterKeys.getOauthconsumerkey())
                .setOAuthConsumerSecret(twitterKeys.getOauthconsumersecret())
                .setOAuthAccessToken(twitterKeys.getOauthaccesstoken())
                .setOAuthAccessTokenSecret(twitterKeys.getOauthaccesstokensecret());
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
        logger.debug("ConfigurationModule construction ended");
    }

    /**
     * The method that provides the injection of a twitter object
     *
     * @return Twitter
     */
    @Provides
    public Twitter provideTwitter() {
        return twitter;
    }
}

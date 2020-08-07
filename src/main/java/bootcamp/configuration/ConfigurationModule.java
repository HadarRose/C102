package bootcamp.configuration;

import dagger.Module;
import dagger.Provides;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

// todo cleanup
// todo logs?
// todo docs

@Module
public class ConfigurationModule {
    private Twitter twitter;

    public ConfigurationModule(ApplicationConfiguration applicationConfiguration){
        TwitterKeys twitterKeys = applicationConfiguration.getTwitterKeys();
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(twitterKeys.getOauthconsumerkey())
                .setOAuthConsumerSecret(twitterKeys.getOauthconsumersecret())
                .setOAuthAccessToken(twitterKeys.getOauthaccesstoken())
                .setOAuthAccessTokenSecret(twitterKeys.getOauthaccesstokensecret());
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    @Provides
    public Twitter provideTwitter(){
        return twitter;
    }
}

import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class PostTweet {
    public static void PostTweet() throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        StatusUpdate statusUpdate = new StatusUpdate("Hello World!");
        Status status = twitter.updateStatus(statusUpdate);
    }
}

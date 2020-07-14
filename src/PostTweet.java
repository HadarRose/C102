import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class PostTweet {
    public static void postTweet(String post) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        StatusUpdate statusUpdate = new StatusUpdate(post);
        Status status = twitter.updateStatus(statusUpdate);
    }
}

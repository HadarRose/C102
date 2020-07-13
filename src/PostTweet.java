import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class PostTweet {
    public static void main(String[] args) throws TwitterException {
        if(args.length == 0) { // check for error: no argument
            throw new IllegalArgumentException("PostTweet.java must have an argument");
        }
        postTweet(args[0]);

    }

    public static void postTweet(String post) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        StatusUpdate statusUpdate = new StatusUpdate(post);
        Status status = twitter.updateStatus(statusUpdate);
    }
}

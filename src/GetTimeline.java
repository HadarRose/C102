import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class GetTimeline {

    public static void getForeignTimeline(String key, String keySecret, String token, String tokenSecret) throws TwitterException {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setOAuthConsumerKey(key);
        builder.setOAuthConsumerSecret(keySecret);
        builder.setOAuthAccessToken(token);
        builder.setOAuthAccessTokenSecret(tokenSecret);
        Configuration configuration = builder.build();

        TwitterFactory factory = new TwitterFactory(configuration);
        Twitter twitter = factory.getInstance();

        List<Status> statusList = twitter.getHomeTimeline();
        System.out.println("Timeline retrieved");
        for(Status status: statusList){
            System.out.println(status.getUser().getName()+":"+status.getText());
        }
    }

    public static void getTimeline() throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        List<Status> statusList = twitter.getHomeTimeline();
        System.out.println("Timeline retrieved");
        for(Status status: statusList){
            System.out.println(status.getUser().getName()+":"+status.getText());
        }
    }
}

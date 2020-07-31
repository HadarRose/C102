package services.twitter4j;

import model.Message;
import configuration.TwitterKeys;
import model.Tweet;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

public class TwitterResourceService {
    private static Logger logger = LoggerFactory.getLogger(TwitterResourceService.class);
    private Twitter twitter;

    /**
     * Constructor
     *
     * @param twitterKeys TwitterKeys containing keys for new twitter object
     */
    public TwitterResourceService(TwitterKeys twitterKeys) {
        logger.info("TwitterResourceService created");
        //TwitterCreationService twitterCreationService = new TwitterCreationService(twitterKeys);
        twitter = this.createTwitter(twitterKeys);
    }

    /**
     * Constructor. For unit testing.
     *
     * @param twitter Twitter value for twitter property
     */
    public TwitterResourceService(Twitter twitter) {
        this.twitter = twitter;
    }

    /**
     * @param twitterKeys twitter keys for ConfigurationBuilder to use
     * @return ConfigurationBuilder with the credentials received from applicationConfiguration
     */
    private ConfigurationBuilder createConfigBuilder(TwitterKeys twitterKeys) {
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
     * @param twitterKeys twitter keys that will be used by createConfigBuilder to create a configuration
     * @return Twitter with loaded credentials
     */
    public Twitter createTwitter(TwitterKeys twitterKeys) {
        logger.info("TwitterCreationService called createTwitter");
        TwitterFactory tf = new TwitterFactory(this.createConfigBuilder(twitterKeys).build());
        return tf.getInstance();
    }


    /**
     * Converts Status object to Tweet object
     *
     * @param status Status object to be converted
     * @return Tweet version of status
     */
    public Tweet statusToTweet(Status status) {
        User user = new User(status.getUser().getScreenName(),
                status.getUser().getName(), status.getUser().getProfileImageURL());
        return new Tweet(status.getText(), status.getCreatedAt(), user);
    }

    /**
     * @return List<Tweet> containing Status representation for tweets from the timeline
     * @throws TwitterResourceException
     */
    public List<Tweet> getTimeline() throws TwitterResourceException {
        logger.info("TwitterResourceService called getTimeline");
        try {
            List<Status> statuses = twitter.getHomeTimeline();
            List<Tweet> tweets = new ArrayList<Tweet>();
            for (Status status : statuses) {
                tweets.add(this.statusToTweet(status));
            }
            return tweets;
        } catch (Exception e) {
            throw new TwitterResourceException(e);
        }
    }

    /**
     * @param post Message containing content of tweet to be posted
     * @return Tweet containing information regarding the uploaded message
     * @throws TwitterResourceException
     */
    public Tweet postTweet(Message post) throws TwitterResourceException {
        logger.info("TwitterResourceService called postTweet");
        try {
            StatusUpdate statusUpdate = new StatusUpdate(post.getMessage());
            Status status = twitter.updateStatus(statusUpdate);
            return this.statusToTweet(status);
        } catch (Exception e) {
            throw new TwitterResourceException(e);
        }
    }
}

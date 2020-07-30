package services.twitter4j;

import model.Message;
import model.TwitterKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;

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
        TwitterCreationService twitterCreationService = new TwitterCreationService(twitterKeys);
        twitter = twitterCreationService.createTwitter();
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
     * @return List<Status> containing Status representation for tweets from the timeline
     * @throws TwitterResourceException
     */
    public List<Status> getTimeline() throws TwitterResourceException {
        logger.info("TwitterResourceService called getTimeline");
        try {
            return twitter.getHomeTimeline();
        } catch (Exception e) {
            throw new TwitterResourceException(e);
        }
    }

    /**
     * @param post Message containing content of tweet to be posted
     * @return Status containing information regarding the uploaded message
     * @throws TwitterResourceException
     */
    public Status postTweet(Message post) throws TwitterResourceException {
        logger.info("TwitterResourceService called postTweet");
        try {
            StatusUpdate statusUpdate = new StatusUpdate(post.getMessage());
            return twitter.updateStatus(statusUpdate);
        } catch (Exception e) {
            throw new TwitterResourceException(e);
        }
    }
}

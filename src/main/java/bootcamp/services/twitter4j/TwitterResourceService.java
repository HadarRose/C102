package bootcamp.services.twitter4j;

import bootcamp.model.Message;
import bootcamp.model.Tweet;
import bootcamp.model.User;
import dagger.Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Module
public class TwitterResourceService {
    private static Logger logger = LoggerFactory.getLogger(TwitterResourceService.class);
    private Twitter twitter;

    /**
     * Constructor.
     *
     * @param twitter Twitter value for twitter property
     */
    @Inject
    public TwitterResourceService(Twitter twitter) {
        this.twitter = twitter;
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
     * @return Optional<List < Tweet>> containing Status representation for tweets from the timeline
     */
    public Optional<List<Tweet>> getTimeline() {
        logger.info("TwitterResourceService called getTimeline");
        try {
            return Optional.ofNullable(twitter.getHomeTimeline().stream() // stream statuses
                    .map(this::statusToTweet) // convert statuses to tweets
                    .collect(Collectors.toList())); // collect as list of tweets and wrap as optional
        } catch (Exception e) {
            TwitterResourceExceptionHandler exceptionHandler = new TwitterResourceExceptionHandler(e);
            logger.error("Error status: " + exceptionHandler.getStatusCode() + " with message: " + exceptionHandler.getMessage());
            return Optional.empty();
        }
    }

    /**
     * @param post Message containing content of tweet to be posted
     * @return Optional<Tweet> containing information regarding the uploaded message
     */
    public Optional<Tweet> postTweet(Message post) {
        logger.info("TwitterResourceService called postTweet");
        try {
            StatusUpdate statusUpdate = new StatusUpdate(post.getMessage());
            return Optional.ofNullable(twitter.updateStatus(statusUpdate))
                    .map(status -> statusToTweet(status));
        } catch (Exception e) {
            TwitterResourceExceptionHandler exceptionHandler = new TwitterResourceExceptionHandler(e);
            logger.error("Error status: " + exceptionHandler.getStatusCode() + " with message: " + exceptionHandler.getMessage());
            return Optional.empty();
        }
    }

    /**
     * @param keyword word by which the timeline will be filtered
     * @return Optional<List < Tweet>> list of Tweet objects from the user's timeline, filtered by keyword
     */
    public Optional<List<Tweet>> getTimelineFiltered(Optional<String> keyword) {
        logger.info("TwitterResourceService called getTimelineFiltered with keyword: {}", keyword);
        try {
            List<Status> statuses = twitter.getHomeTimeline();
            String kWord = keyword.get().toLowerCase(); // will throw the NoSuchElementException if keyword does not exist
            return Optional.ofNullable(statuses.stream() // stream statuses
                    .filter(status -> status.getText().toLowerCase().contains(kWord)) // filter statuses
                    .map(this::statusToTweet) // convert to tweets
                    .collect(Collectors.toList())); // collect stream to list, wrap list in Optional
        } catch (Exception e) {
            TwitterResourceExceptionHandler exceptionHandler = new TwitterResourceExceptionHandler(e);
            logger.error("Error status: " + exceptionHandler.getStatusCode() + " with message: " + exceptionHandler.getMessage());
            return Optional.empty();
        }
    }
}

package services.twitter4j;

import model.Message;
import model.StatusList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;

import javax.ws.rs.core.Response;
import java.util.List;

public class TwitterResourceService {
    private static Logger logger = LoggerFactory.getLogger(TwitterResourceService.class);
    private TwitterExceptionHandlerService handler;

    /**
     * Constructor
     */
    public TwitterResourceService() {
        logger.info("TwitterResourceService created");
        handler = new TwitterExceptionHandlerService();
    }

    /**
     * Constructor
     *
     * @param handler TwitterExceptionHandlerService to be the service's handler property
     */
    public TwitterResourceService(TwitterExceptionHandlerService handler) {
        this.handler = handler;
    }

    /**
     * @param twitter Twitter from which to retrieve timeline
     * @return Response including timeline
     */
    public Response getTimeline(Twitter twitter) {
        logger.info("TwitterResourceService called getTimeline");
        try {
            List<Status> statusList = twitter.getHomeTimeline();
            Response r = Response.ok(new StatusList(statusList)).build();
            logger.debug("getTimline() is returning response: {}", r.toString());
            return r;
        } catch (Exception e) {
            logger.error("Exception was thrown: {}. Twitter corresponding to this event: {}.", e.getMessage(), twitter.toString(), e);
            return handler.ResponseBuilder(e);
        }
    }

    /**
     * @param twitter Twitter to which tweet will be posted
     * @param post    Message containing content of tweet to be posted
     * @return Response containing tweet posted
     */
    public Response postTweet(Twitter twitter, Message post) {
        logger.info("TwitterResourceService called postTweet");
        try {
            logger.debug("postTweet(message) read the message: {}", post.getMessage());
            StatusUpdate statusUpdate = new StatusUpdate(post.getMessage());
            Status status = twitter.updateStatus(statusUpdate);
            Response r = Response.ok(status).build();
            logger.debug("postTweet(message) is returning {}", r.toString());
            return r;
        } catch (Exception e) {
            logger.debug("Twitter when exception thrown: {}", twitter.toString());
            try {
                logger.debug("The message found when the error was throw was: {}", post.getMessage());
            } catch (Exception internalE) {
                logger.error("There was an issue retrieving the message.");
            }
            logger.error(e.getMessage(), e);
            return handler.ResponseBuilder(e);
        }
    }
}

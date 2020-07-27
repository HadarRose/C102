package services.twitter4j;

import handlers.ExceptionHandler;
import model.Message;
import model.StatusList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;

import javax.ws.rs.core.Response;
import java.util.List;

public class TwitterResourceServices {
    private static Logger logger = LoggerFactory.getLogger(TwitterResourceServices.class);

    // TODO resources services

    public Response getTimeline(Twitter twitter, ExceptionHandler exceptionHandler){
        // TODO: hopefully remove exception handler input
        try {
            List<Status> statusList = twitter.getHomeTimeline();
            Response r  = Response.ok(new StatusList(statusList)).build();
            logger.debug("getTimline() is returning response: {}", r.toString());
            return r;
        } catch (Exception e) {
            logger.error("Exception was thrown: {}. Twitter corresponding to this event: {}.", e.getMessage(), twitter.toString(), e);
            return exceptionHandler.ResponseBuilder(e);
        }
    }

    public Response postTweet(Message post, Twitter twitter, ExceptionHandler exceptionHandler){
        // TODO: hopefully remove exception handler input
        try {
            logger.debug("postTweet(message) read the message: {}", post.getMessage());
            StatusUpdate statusUpdate = new StatusUpdate(post.getMessage());
            Status status = twitter.updateStatus(statusUpdate);
            Response r = Response.ok(status).build();
            logger.debug("postTweet(message) is returning {}", r.toString());
            return r;
        } catch (Exception e) {
            logger.debug("Twitter when exception thrown: {}", twitter.toString());
            try{
                logger.debug("The message found when the error was throw was: {}", post.getMessage());
            }catch (Exception internalE){
                logger.error("There was an issue retrieving the message.");
            }
            logger.error(e.getMessage(), e);
            return exceptionHandler.ResponseBuilder(e);
        }

    }

}

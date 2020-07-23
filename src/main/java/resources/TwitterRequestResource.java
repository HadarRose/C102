package resources;

import handlers.ExceptionHandler;
import model.StatusList;
import model.Message;
import configuration.Configuration;

import twitter4j.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/api/1.0/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class TwitterRequestResource {
    public final String VERSION = "1.0";
    private Twitter twitter;
    private ExceptionHandler exceptionHandler;
    private static Logger logger = LoggerFactory.getLogger(TwitterRequestResource.class);

    // CONSTRUCTORS
    /** Constructor, sets up twitter using default configuration settings and exceptionHandler to a new ExceptionHandler
     * */
    public TwitterRequestResource() {
        Configuration c = new Configuration();
        twitter = c.createTwitter();
        exceptionHandler = new ExceptionHandler();
        logger.debug("TwitterRequestResource created with empty constructor");
    }

    /** Constructor
     * @param twitter Twitter to be set at the new twitter property
     * @param exceptionHandler ExceptionHandler to be set as the new exceptionHandler property
     * */
    public TwitterRequestResource(Twitter twitter, ExceptionHandler exceptionHandler) {
        this.twitter = twitter;
        this.exceptionHandler = exceptionHandler;
        logger.debug("TwitterRequestResource created with twitter = {} and exceptionHandler = {}", twitter.toString(), exceptionHandler.toString());
    }

    // GETTERS
    /** @return Twitter. The object's twitter property.
     * */
    public Twitter getTwitter(){
        logger.debug("getTwitter() was called to return {}", this.twitter.toString());
        return this.twitter;
    }

    /** @return ExceptionHandler. The object's exceptionHandler property.
     * */
    public ExceptionHandler getExceptionHandler(){
        logger.debug("getExceptionHandler() was called to return {}", this.exceptionHandler.toString());
        return this.exceptionHandler;
    }

    // API CALLS
    /** API call on /timeline
     * @return Response. Contains list of statuses if successful, or error message if not.
     * */
    @GET
    @Path("/timeline")
    public Response getTimeline() {
        logger.info("GET request at /api/" + VERSION + "/twitter/timeline was triggered");
        try {
            List<Status> statusList = twitter.getHomeTimeline();
            Response r  = Response.ok(new StatusList(statusList)).build();
            logger.debug("getTimline() is returning response: {}", r.toString());
            return r;
        } catch (Exception e) {
            logger.debug("Twitter when exception thrown: {}", this.twitter.toString());
            logger.error(e.getMessage(), e); // log error
            return exceptionHandler.ResponseBuilder(e);
        }
    }

    /** API call on /tweet
     * @param post Message. Contains the content of the tweet to be posted.
     * @return Response. Contains the posted tweet if successful, or error message if not.
     * */
    @POST
    @Path("/tweet")
    public Response postTweet(Message post) {
        logger.info("POST request at /api/" + VERSION + "/twitter/tweet was triggered");
        try {
            logger.debug("postTweet(message) read the message: {}", post.getMessage());
            StatusUpdate statusUpdate = new StatusUpdate(post.getMessage());
            Status status = twitter.updateStatus(statusUpdate);
            Response r = Response.ok(status).build();
            logger.debug("postTweet(message) is returning {}", r.toString());
            return r;
        } catch (Exception e) {
            logger.debug("Twitter when exception thrown: {}", this.twitter.toString());
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

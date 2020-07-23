package resources;

import configuration.Configuration;
import handlers.ExceptionHandler;
import model.StatusList;
import model.Message;

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
    public TwitterRequestResource() {
        Configuration c = new Configuration();
        twitter = c.createTwitter();
        exceptionHandler = new ExceptionHandler();
        logger.debug("TwitterRequestResource created with empty constructor");
    }

    public TwitterRequestResource(Twitter twitter, ExceptionHandler exceptionHandler) {
        this.twitter = twitter;
        this.exceptionHandler = exceptionHandler;
        logger.debug("TwitterRequestResource created with twitter = {} and exceptionHandler = {}", twitter, exceptionHandler);
    }
    // GETTERS
    public Twitter getTwitter(){
        logger.debug("getTwitter() was called to return {}", this.twitter);
        return this.twitter;
    }

    public ExceptionHandler getExceptionHandler(){
        logger.debug("getExceptionHandler() was called to return {}", this.exceptionHandler);
        return this.exceptionHandler;
    }
    // API CALLS
    @GET
    @Path("/timeline")
    public Response getTimeline() {
        logger.info("GET request at /api/" + VERSION + "/twitter/timeline was triggered");
        try {
            List<Status> statusList = twitter.getHomeTimeline();
            Response r  = Response.ok(new StatusList(statusList)).build();
            logger.debug("getTimline() is returning response: {}", r);
            return r;
        } catch (Exception e) {
            logger.debug("Twitter when exception thrown: {}", this.twitter);
            logger.error(e.getMessage(), e); // log error
            return exceptionHandler.ResponseBuilder(e);
        }
    }

    @POST
    @Path("/tweet")
    public Response postTweet(Message post) {
        logger.info("POST request at /api/" + VERSION + "/twitter/tweet was triggered");
        try {
            logger.debug("postTweet(message) read the message: {}", post.getMessage());
            StatusUpdate statusUpdate = new StatusUpdate(post.getMessage());
            Status status = twitter.updateStatus(statusUpdate);
            Response r = Response.ok(status).build();
            logger.debug("postTweet(message) is returning {}", r);
            return r;
        } catch (Exception e) {
            logger.debug("Twitter when exception thrown: {}", this.twitter);
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

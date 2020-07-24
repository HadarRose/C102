package resources;

import configuration.Configuration;
import model.ErrorMessage;
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
    private static Logger LOGGER = LoggerFactory.getLogger(ErrorMessage.class);

    // CONSTRUCTORS
    public TwitterRequestResource() {
        Configuration c = new Configuration();
        twitter = c.createTwitter();
        exceptionHandler = new ExceptionHandler();
    }

    public TwitterRequestResource(Twitter twitter, ExceptionHandler exceptionHandler) {
        this.twitter = twitter;
        this.exceptionHandler = exceptionHandler;
    }
    // GETTERS
    public Twitter getTwitter(){
        return this.twitter;
    }

    public ExceptionHandler getExceptionHandler(){
        return this.exceptionHandler;
    }
    // API CALLS
    @GET
    @Path("/timeline")
    public Response getTimeline() {
        LOGGER.info("GET request at /api/" + VERSION + "/twitter/timeline was triggered");
        try {
            List<Status> statusList = twitter.getHomeTimeline();
            return Response.ok(new StatusList(statusList)).build();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e); // log error
            return exceptionHandler.ResponseBuilder(e);
        }
    }

    @POST
    @Path("/tweet")
    public Response postTweet(Message post) {
        LOGGER.info("POST request at /api/" + VERSION + "/twitter/tweet was triggered");
        try {
            StatusUpdate statusUpdate = new StatusUpdate(post.getMessage());
            Status status = twitter.updateStatus(statusUpdate);
            return Response.ok(status).build();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e); // log error
            return exceptionHandler.ResponseBuilder(e);
        }
    }
}

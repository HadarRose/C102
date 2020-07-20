package resources;

import api.ErrorMessage;
import api.ExceptionHandler;
import api.StatusList;
import api.Message;

import twitter4j.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/api/1.0/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class TwitterRequestHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(ErrorMessage.class);

    public TwitterRequestHandler(){}

    @GET
    @Path("/timeline")
    public Response getTimeline(){
        LOGGER.info("GET request at /api/1.0/twitter/timeline was triggered");
        try {
            Twitter twitter = TwitterFactory.getSingleton(); // code originally from GetTimeline.java
            List<Status> statusList = twitter.getHomeTimeline();
            System.out.println("Timeline retrieved");
            return Response.ok(new StatusList(statusList)).build();
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e); // log error
            return ExceptionHandler.ResponseBuilder(e);
        }
    }

    @POST
    @Path("/tweet")
    public Response postTweet(Message post){
        LOGGER.info("POST request at /api/1.0/twitter/tweet was triggered");
        try{
            Twitter twitter = TwitterFactory.getSingleton(); // code originally from PostTweet.java
            StatusUpdate statusUpdate = new StatusUpdate(post.message);
            Status status = twitter.updateStatus(statusUpdate);
            System.out.println("Successfully updated the status to [" + status.getText() + "].");
            return Response.ok("Successfully updated the status to [" + status.getText() + "].").build();
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e); // log error
            return ExceptionHandler.ResponseBuilder(e);
        }
    }
}

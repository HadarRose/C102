package resources;

import model.ErrorMessage;
import model.Message;

import services.twitter4j.TwitterExceptionHandlerService;
import services.twitter4j.TwitterResourceService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;

@Path("/api/1.0/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class TwitterRequestResource {
    private static Logger logger = LoggerFactory.getLogger(TwitterRequestResource.class);
    private TwitterResourceService twitterResourceService;
    private TwitterExceptionHandlerService handlerService;
    public final String VERSION = "1.0";

    /**
     * Constructor, sets up twitter using default configuration settings and exceptionHandler to a new ExceptionHandler
     */
    public TwitterRequestResource() throws IOException {
        twitterResourceService = new TwitterResourceService();
        handlerService = new TwitterExceptionHandlerService();
        logger.debug("TwitterRequestResource created with empty constructor");
    }

    /**
     * Constructor. For unit testing.
     *
     * @param twitterResourceService TwitterResourceService value for twitterResourceService property
     */
    public TwitterRequestResource(TwitterResourceService twitterResourceService, TwitterExceptionHandlerService handlerService) {
        this.twitterResourceService = twitterResourceService;
        this.handlerService = handlerService;
    }

    /**
     * API call on /timeline
     *
     * @return Response. Contains list of statuses if successful, or error message if not.
     */
    @GET
    @Path("/timeline")
    public Response getTimeline() {
        logger.info("GET request at /api/" + VERSION + "/twitter/timeline was triggered");
        try{
            List<Status> statusList = twitterResourceService.getTimeline();
            return Response.ok(statusList).build();
        } catch (Exception e){
            ErrorMessage errorMessage = handlerService.ErrorMessageBuilder(e);
            return Response.status(errorMessage.getStatusCode()).entity(errorMessage).build();
        }
    }

    /**
     * API call on /tweet
     *
     * @param post Message. Contains the content of the tweet to be posted.
     * @return Response. Contains the posted tweet if successful, or error message if not.
     */
    @POST
    @Path("/tweet")
    public Response postTweet(Message post) {
        logger.info("POST request at /api/" + VERSION + "/twitter/tweet was triggered");
        try{
            Status status = twitterResourceService.postTweet(post);
            return Response.ok(status).build();
        } catch (Exception e){
            ErrorMessage errorMessage = handlerService.ErrorMessageBuilder(e);
            return Response.status(errorMessage.getStatusCode()).entity(errorMessage).build();
        }
    }
}

package resources;

import model.Message;

import services.twitter4j.TwitterResourceService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/api/1.0/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class TwitterRequestResource {
    private static Logger logger = LoggerFactory.getLogger(TwitterRequestResource.class);
    private TwitterResourceService twitterResourceService;
    public final String VERSION = "1.0";

    /**
     * Constructor, sets up twitter using default configuration settings and exceptionHandler to a new ExceptionHandler
     */
    public TwitterRequestResource() throws IOException {

        twitterResourceService = new TwitterResourceService();
        logger.debug("TwitterRequestResource created with empty constructor");
    }

    /**
     * Constructor. For unit testing.
     *
     * @param twitterResourceService TwitterResourceService value for twitterResourceService property
     */
    public TwitterRequestResource(TwitterResourceService twitterResourceService) {
        this.twitterResourceService = twitterResourceService;
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
        return twitterResourceService.getTimeline();
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
        return twitterResourceService.postTweet(post);
    }
}

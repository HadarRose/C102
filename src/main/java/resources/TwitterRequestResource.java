package resources;

import model.ErrorMessage;
import model.Message;

import model.TwitterKeys;
import services.twitter4j.TwitterResourceException;
import services.twitter4j.TwitterResourceService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;

@Path("/api/1.0/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class TwitterRequestResource {
    private static Logger logger = LoggerFactory.getLogger(TwitterRequestResource.class);
    private TwitterResourceService twitterResourceService;
    public final String VERSION = "1.0";

    /**
     * Constructor, sets up twitter using passed configuration settings
     *
     * @param twitterKeys TwitterKeys containing keys for new twitter object
     */
    public TwitterRequestResource(TwitterKeys twitterKeys) {
        twitterResourceService = new TwitterResourceService(twitterKeys);
        logger.debug("TwitterRequestResource created with twitter keys");
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
        try {
            List<Status> statusList = twitterResourceService.getTimeline();
            return Response.ok(statusList).build();
        } catch (TwitterResourceException e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getStatusCode(), e.getMessage());
            return Response.status(e.getStatusCode()).entity(errorMessage).build();
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
        try {
            Status status = twitterResourceService.postTweet(post);
            return Response.ok(status).build();
        } catch (TwitterResourceException e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getStatusCode(), e.getMessage());
            return Response.status(e.getStatusCode()).entity(errorMessage).build();
        }
    }
}

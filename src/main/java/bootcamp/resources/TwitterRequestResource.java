package bootcamp.resources;

import bootcamp.model.ErrorMessage;
import bootcamp.model.Message;

import bootcamp.configuration.TwitterKeys;
import bootcamp.services.twitter4j.TwitterResourceException;
import bootcamp.services.twitter4j.TwitterResourceService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;
import java.util.Optional;

@Path("/api/1.0/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class TwitterRequestResource {
    private static Logger logger = LoggerFactory.getLogger(TwitterRequestResource.class);
    private static final String SERVICE_ERROR = "Something went wrong.";
    private TwitterResourceService twitterResourceService;
    public final String VERSION = "1.0";

    /**
     * Constructor, sets up twitter using passed bootcamp.configuration settings
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
            return twitterResourceService.getTimeline() // Optional<List<Tweet>>
                    .map(tweets -> Response.ok(tweets).build()) // Optional<Response>
                    .get(); // Response;
        } catch (TwitterResourceException e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getStatusCode(), e.getMessage());
            return Response.status(e.getStatusCode()).entity(errorMessage).build();
        } catch (NoSuchElementException e){
            ErrorMessage errorMessage = new ErrorMessage(500, TwitterRequestResource.SERVICE_ERROR);
            logger.error("TwitterResourceService returned an empty optional.");
            return Response.status(500).entity(errorMessage).build();
        }
    }

    /**
     * API call to /timeline/filter?keyword={keyword}
     *
     * @return Response. Contains list of filtered tweets if successful, or error message if not.
     */
    @GET
    @Path("/timeline/filter")
    public Response getTimelineFiltered(@QueryParam("keyword") Optional<String> keyword) {
        logger.info("GET request at /api/" + VERSION + "/twitter/timeline/filter?keyword=" + keyword + " was triggered");
        try {
            return twitterResourceService.getTimelineFiltered(keyword) // Optional<List<Tweet>> from service
                    .map(tweets -> Response.ok(tweets).build()) // Optional<Response>
                    .get(); // Response
        } catch (TwitterResourceException e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getStatusCode(), e.getMessage());
            return Response.status(e.getStatusCode()).entity(errorMessage).build();
        } catch (NoSuchElementException e){
            ErrorMessage errorMessage = new ErrorMessage(500, TwitterRequestResource.SERVICE_ERROR);
            logger.error("TwitterResourceService returned an empty optional.");
            return Response.status(500).entity(errorMessage).build();
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
            return twitterResourceService.postTweet(post) // Optional<Tweet> from service
                    .map(t -> Response.ok(t).build()) // Optional<Response>
                    .get(); // Response
        } catch (TwitterResourceException e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getStatusCode(), e.getMessage());
            return Response.status(e.getStatusCode()).entity(errorMessage).build();
        } catch (NoSuchElementException e){
            ErrorMessage errorMessage = new ErrorMessage(500, TwitterRequestResource.SERVICE_ERROR);
            logger.error("TwitterResourceService returned an empty optional.");
            return Response.status(500).entity(errorMessage).build();
        }
    }
}

package bootcamp.resources;

import bootcamp.model.ErrorMessage;
import bootcamp.model.Message;

import bootcamp.configuration.TwitterKeys;
import bootcamp.model.Tweet;
import bootcamp.services.twitter4j.TwitterResourceException;
import bootcamp.services.twitter4j.TwitterResourceService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Path("/api/1.0/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class TwitterRequestResource {
    private static Logger logger = LoggerFactory.getLogger(TwitterRequestResource.class);
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
            List<Tweet> tweetList = twitterResourceService.getTimeline();
            return Response.ok(tweetList).build();
        } catch (TwitterResourceException e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getStatusCode(), e.getMessage());
            return Response.status(e.getStatusCode()).entity(errorMessage).build();
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
            List<Tweet> tweetList = twitterResourceService.getTimelineFiltered(keyword);
            return Response.ok(tweetList).build();
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
            Tweet tweet = twitterResourceService.postTweet(post);
            return Response.ok(tweet).build();
        } catch (TwitterResourceException e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getStatusCode(), e.getMessage());
            return Response.status(e.getStatusCode()).entity(errorMessage).build();
        }
    }
}

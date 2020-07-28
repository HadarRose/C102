package resources;

import model.Message;

import services.twitter4j.TwitterCreationService;
import services.twitter4j.TwitterResourceService;
import twitter4j.*;

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
    private Twitter twitter;

    /**
     * Constructor, sets up twitter using default configuration settings and exceptionHandler to a new ExceptionHandler
     */
    public TwitterRequestResource() throws IOException {
        TwitterCreationService twitterCreationService = new TwitterCreationService();
        twitter = twitterCreationService.createTwitter();
        twitterResourceService = new TwitterResourceService();
        logger.debug("TwitterRequestResource created with empty constructor");
    }

    /**
     * Constructor
     *
     * @param twitter Twitter to be set at the new twitter property
     */
    public TwitterRequestResource(Twitter twitter, TwitterResourceService twitterResourceService) {
        this.twitter = twitter;
        this.twitterResourceService = twitterResourceService;
        logger.debug("TwitterRequestResource created with twitter = {}", twitter.toString());
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
        return twitterResourceService.getTimeline(this.twitter);
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
        return twitterResourceService.postTweet(this.twitter, post);
    }
}

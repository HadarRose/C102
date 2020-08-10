package bootcamp.resources;

import bootcamp.model.ErrorMessage;
import bootcamp.model.Message;

import bootcamp.services.twitter4j.TwitterResourceService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Path("/api/1.0/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class TwitterRequestResource {
    private static Logger logger = LoggerFactory.getLogger(TwitterRequestResource.class);
    public static final String SERVICE_ERROR = "Something went wrong.";
    private static final ErrorMessage ERROR_MESSAGE = new ErrorMessage(500, TwitterRequestResource.SERVICE_ERROR);
    private TwitterResourceService twitterResourceService;
    public final String VERSION = "1.0";


    /**
     * Constructor
     *
     * @param twitterResourceService TwitterResourceService value for twitterResourceService property
     */
    @Inject
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
        return twitterResourceService.getTimeline() // Optional<List<Tweet>>
                .map(tweets -> Response.ok(tweets).build()) // Optional<Response>
                .orElse(Response.status(500).entity(ERROR_MESSAGE).build()); // Response (error or mapped)
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
        return twitterResourceService.getTimelineFiltered(keyword) // Optional<List<Tweet>> from service
                .map(tweets -> Response.ok(tweets).build()) // Optional<Response>
                .orElse(Response.status(500).entity(ERROR_MESSAGE).build()); // Response (error or mapped)
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
        return twitterResourceService.postTweet(post) // Optional<Tweet> from service
                .map(t -> Response.ok(t).build()) // Optional<Response>
                .orElse(Response.status(500).entity(ERROR_MESSAGE).build()); // Response (error or mapped)
    }
}

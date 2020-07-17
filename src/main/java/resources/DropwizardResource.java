package resources;

import api.ErrorMessage;
import api.StatusList;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import twitter4j.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/api/1.0/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class DropwizardResource {

    public DropwizardResource(){}

    @GET
    @Path("/timeline")
    public Response getTimeline(){
        try {
            Twitter twitter = TwitterFactory.getSingleton(); // code originally from GetTimeline.java
            List<Status> statusList = twitter.getHomeTimeline();
            System.out.println("Timeline retrieved");
            return Response.ok(new StatusList(statusList)).build();
        } catch (TwitterException e){
            return Response.status(e.getStatusCode()).entity(new ErrorMessage(e)).build();

        }
    }

    @POST
    @Path("/tweet")
    public Response postTweet(@Valid @NotNull Message post){ // TODO: optional? required? passed as query? what is the proper patter for passing info in post calls
        try{
            Twitter twitter = TwitterFactory.getSingleton(); // code originally from PostTweet.java
            StatusUpdate statusUpdate = new StatusUpdate(post.message);
            Status status = twitter.updateStatus(statusUpdate);
            System.out.println("Successfully updated the status to [" + status.getText() + "].");
            return Response.ok("Successfully updated the status to [" + status.getText() + "].").build();
        } catch (TwitterException e){
            return Response.status(e.getStatusCode()).entity(new ErrorMessage(e)).build();
        }
    }

    public static class Message{
        @JsonProperty("message")
        @NotEmpty
        @Length(max = 280, min = 1)
        public String message;
    }

}

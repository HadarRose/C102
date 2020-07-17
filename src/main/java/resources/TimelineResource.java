//    // TODO: make sure you got rid of this

//package resources;
//
//import api.ErrorMessage;
//import api.Post;
//import api.StatusList;
//import twitter4j.*;
//
//import javax.ws.rs.*;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.MediaType;
//import java.util.List;
//
//@Path("/api/1.0/twitter")
//@Produces(MediaType.APPLICATION_JSON)
//public class TimelineResource {
//
//    public TimelineResource(){}
//
//    @GET
//    @Path("/timeline")
//    public Response getTimeline(){
//        try {
//            //List<Status> statusList = GetTimeline.getTimeline();
//            Twitter twitter = TwitterFactory.getSingleton(); // code originally from GetTimeline.java
//            List<Status> statusList = twitter.getHomeTimeline();
//            System.out.println("Timeline retrieved");
//            return Response.ok(new StatusList(statusList)).build();
//        } catch (TwitterException e){
//            return Response.status(e.getStatusCode()).entity(new ErrorMessage(e)).build();
//
//        }
//    }
//
//    @POST
//    @Path("/tweet")
//    public Response postTweet(@QueryParam("message") String post){
//        try{
//            Twitter twitter = TwitterFactory.getSingleton(); // code originally from PostTweet.java
//            StatusUpdate statusUpdate = new StatusUpdate(post);
//            Status status = twitter.updateStatus(statusUpdate);
//            System.out.println("Successfully updated the status to [" + status.getText() + "].");
//            return Response.ok("Successfully updated the status to [" + status.getText() + "].").build();
//        } catch (TwitterException e){
//            return Response.status(e.getStatusCode()).entity(new ErrorMessage(e)).build();
//        }
//    }
//}

package bootcamp.resources;

import bootcamp.model.ErrorMessage;
import bootcamp.model.Message;
import bootcamp.model.Tweet;
import org.junit.Before;
import org.junit.Test;
import bootcamp.services.twitter4j.TwitterResourceException;
import bootcamp.services.twitter4j.TwitterResourceService;
import twitter4j.Status;

import javax.ws.rs.core.Response;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class TwitterRequestResourceTest {
    private TwitterResourceService mockedResourceServices;

    @Before
    public void initialize() {
        mockedResourceServices = Mockito.mock(TwitterResourceService.class);
    }

    // the following tests test that the methods call the correct bootcamp.services and return the correct responses
    @Test
    public void validTimeline() throws TwitterResourceException {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedResourceServices);
        List<Tweet> mockedList = Mockito.mock(List.class);
        Mockito.when(mockedResourceServices.getTimeline()).thenReturn(mockedList);

        Response r = twitterRequestResource.getTimeline();
        Mockito.verify(mockedResourceServices, Mockito.times(1)).getTimeline();
        Assert.assertEquals(200, r.getStatus());
        List<Status> statusList = (List<Status>) r.getEntity();
        Assert.assertEquals(mockedList, statusList);
    }

    @Test
    public void invalidTimeline() throws TwitterResourceException {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedResourceServices);
        TwitterResourceException mockedException = Mockito.mock(TwitterResourceException.class);
        Mockito.when(mockedResourceServices.getTimeline()).thenThrow(mockedException);
        Mockito.when(mockedException.getStatusCode()).thenReturn(500);
        Mockito.when(mockedException.getMessage()).thenReturn("This is an error");

        Response r = twitterRequestResource.getTimeline();
        Mockito.verify(mockedResourceServices, Mockito.times(1)).getTimeline();
        Assert.assertEquals(500, r.getStatus());
        ErrorMessage errorMessage = (ErrorMessage) r.getEntity();
        Assert.assertEquals("This is an error", errorMessage.getMessage());
    }

    @Test
    public void validPost() throws TwitterResourceException {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedResourceServices);
        Tweet mockedTweet = Mockito.mock(Tweet.class);
        Message mockedMessage = Mockito.mock(Message.class);
        Mockito.when(mockedResourceServices.postTweet(Matchers.any(Message.class))).thenReturn(mockedTweet);

        Response r = twitterRequestResource.postTweet(mockedMessage);
        Tweet tweet = (Tweet) r.getEntity();
        Mockito.verify(mockedResourceServices, Mockito.times(1)).postTweet(Matchers.any(Message.class));
        Assert.assertEquals(200, r.getStatus());
        Assert.assertEquals(mockedTweet, tweet);
    }

    @Test
    public void invalidPost() throws TwitterResourceException {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedResourceServices);
        Message mockedMessage = Mockito.mock(Message.class);
        TwitterResourceException mockedException = Mockito.mock(TwitterResourceException.class);
        Mockito.when(mockedResourceServices.postTweet(Matchers.any(Message.class))).thenThrow(mockedException);
        Mockito.when(mockedException.getStatusCode()).thenReturn(500);
        Mockito.when(mockedException.getMessage()).thenReturn("This is an error");

        Response r = twitterRequestResource.postTweet(mockedMessage);
        ErrorMessage errorMessage = (ErrorMessage) r.getEntity();
        Mockito.verify(mockedResourceServices, Mockito.times(1)).postTweet(Matchers.any(Message.class));
        Assert.assertEquals(500, r.getStatus());
        Assert.assertEquals("This is an error", errorMessage.getMessage());
    }
}